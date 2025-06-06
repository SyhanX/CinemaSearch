package com.syhan.cinemasearch.core.domain.repository

import com.syhan.cinemasearch.core.domain.util.NetworkResult
import com.syhan.cinemasearch.core.domain.model.MovieList

interface MovieRepository {
    suspend fun getMovies() : NetworkResult<MovieList>
}