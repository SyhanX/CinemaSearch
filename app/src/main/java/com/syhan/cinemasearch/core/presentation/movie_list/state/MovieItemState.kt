package com.syhan.cinemasearch.core.presentation.movie_list.state

data class MovieItemState(
    val id: Int = -1,
    val localizedName: String = "",
    val imageUrl: String? = null,
    val onClick: (id: Int) -> Unit = {},
)
