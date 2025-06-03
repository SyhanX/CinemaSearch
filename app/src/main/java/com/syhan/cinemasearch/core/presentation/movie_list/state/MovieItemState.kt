package com.syhan.cinemasearch.core.presentation.movie_list.state

data class MovieItemState(
    val id: Int = -1,
    val localizedName: String = "",
    val name: String = "",
    val year: Int = -1,
    val rating: Float? = null,
    val imageUrl: String? = null,
    val description: String? = null,
    val genres: List<String> = emptyList(),
    val onClick: (id: Int) -> Unit = {},
)
