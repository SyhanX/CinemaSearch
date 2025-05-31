package com.syhan.cinemasearch.core.presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syhan.cinemasearch.core.data.UiState
import com.syhan.cinemasearch.core.data.remote.NetworkError
import com.syhan.cinemasearch.core.data.remote.NetworkResult
import com.syhan.cinemasearch.core.domain.repository.MovieRepository
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
        getMovies()
    }
    
    private fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getMovies()
            when (response) {
                is NetworkResult.Error -> {
                    _state.update {
                        it.copy(
                            uiState = if (response.type == NetworkError.NoInternet) {
                                UiState.ShowNoConnection
                            } else UiState.ShowError
                        )
                    }
                }

                is NetworkResult.Exception -> {
                    _state.update {
                        it.copy(uiState = UiState.ShowError)
                    }
                }

                is NetworkResult.Success -> {
                    _state.update {
                        it.copy(
                            uiState = UiState.ShowContent,
                            movies = response.data
                        )
                    }
                }
            }
        }
    }
}