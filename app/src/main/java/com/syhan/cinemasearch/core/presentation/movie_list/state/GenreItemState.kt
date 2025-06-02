package com.syhan.cinemasearch.core.presentation.movie_list.state

data class GenreItemState(
    val id: Int = -1,
    val name: String = "",
    val isSelected: Boolean = false,
    val onClick: (id: Int) -> Unit = {},
)