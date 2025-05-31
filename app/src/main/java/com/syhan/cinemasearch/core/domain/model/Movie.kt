package com.syhan.cinemasearch.core.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    @SerialName("localized_name")
    val localizedName: String,
    val name: String,
    val year: Int,
    val rating: Float? = null,
    @SerialName("image_url")
    val imageUrl: String? = null,
    val description: String? = null,
    val genres: List<String>
)

@Serializable
data class MovieList(
    val films: List<Movie>
)
