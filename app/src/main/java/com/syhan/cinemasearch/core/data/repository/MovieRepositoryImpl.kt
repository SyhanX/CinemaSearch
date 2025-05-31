package com.syhan.cinemasearch.core.data.repository

import com.syhan.cinemasearch.core.data.remote.MovieApi
import com.syhan.cinemasearch.core.data.remote.makeNetworkRequest
import com.syhan.cinemasearch.core.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val api: MovieApi
) : MovieRepository {
    override suspend fun getMovies() = makeNetworkRequest {
        api.getMovies()
    }
}