package com.syhan.cinemasearch.core.presentation.movie_list.state

import androidx.compose.runtime.Immutable
import com.syhan.cinemasearch.core.data.UiState

@Immutable
data class MovieListState(
    val uiState: UiState = UiState.ShowLoading,
    val movies: List<MovieItemState> = emptyList(),
    val genres: List<GenreItemState> = emptyList()
)