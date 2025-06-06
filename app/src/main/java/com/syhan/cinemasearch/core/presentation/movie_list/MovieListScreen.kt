package com.syhan.cinemasearch.core.presentation.movie_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.syhan.cinemasearch.core.presentation.movie_list.components.ListOfGenresAndMovies
import com.syhan.cinemasearch.core.presentation.movie_list.components.MovieListTopBar
import com.syhan.cinemasearch.core.presentation.movie_list.state.MovieListState
import com.syhan.cinemasearch.core.presentation.state.UiState
import com.syhan.cinemasearch.core.presentation.theme.darkYellow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Suppress("unused")
private const val TAG = "MovieListScreen"

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    onMovieClick: (String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MovieListContent(
        state = state,
        onMovieClick = { id ->
            val serializedMovieData = Json.encodeToString(state.movies.find { it.id == id })
            onMovieClick(serializedMovieData)
        }
    )
}

@Composable
fun MovieListContent(
    state: MovieListState,
    onMovieClick: (Int) -> Unit,
) {
    Scaffold(
        topBar = { MovieListTopBar() },
        modifier = Modifier
            .fillMaxSize()
    ) { contentPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            when (state.uiState) {
                UiState.ShowLoading -> {
                    CircularProgressIndicator(
                        color = darkYellow
                    )
                }

                UiState.ShowError -> {
                    /* Don't show anything */
                }

                UiState.ShowContent -> {
                    val shownList = if (state.selectedGenre == null) {
                        state.movies
                    } else state.filteredMovies
                    ListOfGenresAndMovies(
                        genres = state.genres,
                        movies = shownList,
                        onMovieClick = {
                            onMovieClick(it)
                        }
                    )
                }
            }
        }
    }
}