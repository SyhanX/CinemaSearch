package com.syhan.cinemasearch.core.data.remote

import com.syhan.cinemasearch.core.domain.model.MovieList
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET("/sequeniatesttask/films.json")
    suspend fun getMovies() : Response<MovieList>

}