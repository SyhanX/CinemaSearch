package com.syhan.cinemasearch.core.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syhan.cinemasearch.core.data.Genre
import com.syhan.cinemasearch.core.data.UiState
import com.syhan.cinemasearch.core.data.remote.NetworkResult
import com.syhan.cinemasearch.core.domain.repository.MovieRepository
import com.syhan.cinemasearch.core.presentation.movie_list.state.GenreItemState
import com.syhan.cinemasearch.core.presentation.movie_list.state.MovieItemState
import com.syhan.cinemasearch.core.presentation.movie_list.state.MovieListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "MovieListViewModel"

class MovieListViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MovieListState())
    val state = _state.asStateFlow()

    init {
        loadMovies()
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
    }

    fun loadMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getMovies()
            when (response) {
                is NetworkResult.Error -> {
                    _state.update {
                        it.copy(
                            uiState = UiState.ShowError
                        )
                    }
                }

                is NetworkResult.Exception -> {
                    _state.update {
                        it.copy(
                            uiState = UiState.ShowError,
                        )
                    }
                }

                is NetworkResult.Success -> {
                    _state.update {
                        it.copy(
                            uiState = UiState.ShowContent,
                            movies = response.data.films.map { movie ->
                                MovieItemState(
                                    id = movie.id,
                                    localizedName = movie.localizedName,
                                    name = movie.name,
                                    year = movie.year,
                                    rating = movie.rating,
                                    imageUrl = movie.imageUrl,
                                    description = movie.description,
                                    genres = movie.genres,
                                    onClick = {
                                        // TODO:
                                    }
                                )
                            },
                            genres = Genre.entries.map {
                                GenreItemState(
                                    id = it.ordinal,
                                    name = it.genreName,
                                    isSelected = false,
                                    onClick = {
                                        selectGenre(it)
                                    }
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    fun setLoadingState() {
        _state.update {
            it.copy(
                uiState = UiState.ShowLoading
            )
        }
    }

   /* private fun sortMoviesByGenre(id: Int) {
        val sortedList = state.value.movies.sortedBy { movie ->
            movie.genre?.let { genre ->
                genre == Genre.entries[id]
            } ?:
        }
        _state.update {
            it.copy(
                movies = sortedList
            )
        }
    }*/
}