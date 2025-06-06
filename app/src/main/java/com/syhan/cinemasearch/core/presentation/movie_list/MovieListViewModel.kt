package com.syhan.cinemasearch.core.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syhan.cinemasearch.core.data.remote.NetworkResult
import com.syhan.cinemasearch.core.domain.model.Movie
import com.syhan.cinemasearch.core.domain.repository.MovieRepository
import com.syhan.cinemasearch.core.presentation.movie_list.state.GenreItemState
import com.syhan.cinemasearch.core.presentation.movie_list.state.MovieItemState
import com.syhan.cinemasearch.core.presentation.movie_list.state.MovieListState
import com.syhan.cinemasearch.core.presentation.movie_list.state.Genre
import com.syhan.cinemasearch.core.presentation.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Suppress("unused")
private const val TAG = "MovieListViewModel"

class MovieListViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MovieListState())
    val state = _state.asStateFlow()

    init {
        loadMovies()
    }

    private fun setUiState(uiState: UiState) {
        _state.update {
            it.copy(uiState = uiState)
        }
    }

    private fun setMovieState(movies: List<Movie>) {
        _state.update {
            it.copy(
                movies = movies.map { movie ->
                    MovieItemState(
                        id = movie.id,
                        localizedName = movie.localizedName,
                        name = movie.name,
                        year = movie.year,
                        rating = movie.rating,
                        imageUrl = movie.imageUrl,
                        description = movie.description,
                        genres = movie.genres,
                    )
                }.sortedBy { it.localizedName }
            )
        }
    }

    private fun setGenreState() {
        _state.update {
            it.copy(
                genres = Genre.entries.map { genre ->
                    GenreItemState(
                        id = genre.ordinal,
                        name = genre.genreName,
                        isSelected = false,
                        onClick = { id ->
                            selectGenre(id)
                            filterMoviesByGenre()
                        }
                    )
                }
            )
        }
    }

    private fun selectGenre(id: Int) {
        _state.update { state ->
            state.copy(
                genres = state.genres.map { genre ->
                    if (id == genre.id) {
                        if (genre.isSelected) {
                            genre.copy(isSelected = false)
                        } else genre.copy(isSelected = true)
                    } else genre.copy(isSelected = false)
                }
            )
        }
        _state.update { state ->
            state.copy(
                selectedGenre = state.genres.find { it.isSelected }
            )
        }
    }

    fun loadMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            setUiState(UiState.ShowLoading)
            val response = repository.getMovies()
            when (response) {
                is NetworkResult.Error, is NetworkResult.Exception -> {
                    setUiState(UiState.ShowError)
                }

                is NetworkResult.Success -> {
                    setUiState(UiState.ShowContent)
                    setMovieState(movies = response.data.films)
                    setGenreState()
                }
            }
        }
    }

    private fun filterMoviesByGenre() {
        val filteredList: List<MovieItemState> = state.value.selectedGenre?.let { selectedGenre ->
            state.value.movies.filter { movie ->
                movie.genres.contains(
                    selectedGenre.name.lowercase()
                )
            }.sortedBy { it.localizedName }
        } ?: emptyList()

        _state.update { state ->
            state.copy(
                filteredMovies = filteredList
            )
        }
    }
}