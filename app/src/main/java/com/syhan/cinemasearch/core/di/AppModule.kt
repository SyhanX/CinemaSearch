package com.syhan.cinemasearch.core.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.syhan.cinemasearch.core.data.remote.MovieApi
import com.syhan.cinemasearch.core.data.remote.RetrofitConstants
import com.syhan.cinemasearch.core.data.repository.MovieRepositoryImpl
import com.syhan.cinemasearch.core.domain.repository.MovieRepository
import com.syhan.cinemasearch.core.presentation.movie_details.MovieDetailsViewModel
import com.syhan.cinemasearch.core.presentation.movie_list.MovieListViewModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

// Due to the small scale of the project I found it unnecessary to have multiple DI modules
val appModule = module {
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val customJson = Json { ignoreUnknownKeys = true }

        Retrofit.Builder()
            .baseUrl(RetrofitConstants.BASE_URL)
            .client(client)
            .addConverterFactory(customJson.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(MovieApi::class.java)
    }

    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }

    viewModel {
        MovieListViewModel(get())
    }

    viewModel {
        MovieDetailsViewModel(get())
    }
}