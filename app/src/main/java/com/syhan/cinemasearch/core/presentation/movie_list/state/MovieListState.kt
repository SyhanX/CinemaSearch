package com.syhan.cinemasearch.core.presentation.movie_list.state

import com.syhan.cinemasearch.core.presentation.state.UiState

data class MovieListState(
    val uiState: UiState = UiState.Loading,
    val movies: List<MovieItemState> = emptyList(),
    val filteredMovies: List<MovieItemState> = emptyList(),
    val genres: List<GenreItemState> = emptyList(),
    val selectedGenre: GenreItemState? = null,
)