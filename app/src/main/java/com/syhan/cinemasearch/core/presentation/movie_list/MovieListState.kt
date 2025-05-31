package com.syhan.cinemasearch.core.presentation.movie_list

import androidx.compose.runtime.Immutable
import com.syhan.cinemasearch.core.data.UiState
import com.syhan.cinemasearch.core.domain.model.MovieList

@Immutable
data class MovieListState(
    val uiState: UiState = UiState.ShowLoading,
    val movies: MovieList = MovieList(emptyList())
)