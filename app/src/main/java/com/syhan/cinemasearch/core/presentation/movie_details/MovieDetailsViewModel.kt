package com.syhan.cinemasearch.core.presentation.movie_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.syhan.cinemasearch.core.presentation.movie_list.state.MovieItemState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json

class MovieDetailsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(MovieItemState())
    val state = _state.asStateFlow()

    private val args = savedStateHandle.get<String>("movie_data")

    init {
        _state.update {
            deserializeMovieData()
        }
    }

    private fun deserializeMovieData(): MovieItemState {
        return args?.let {
            Json.decodeFromString<MovieItemState>(it)
        } ?: MovieItemState()
    }
}