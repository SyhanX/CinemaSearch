package com.syhan.cinemasearch.core.presentation.movie_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.syhan.cinemasearch.R
import com.syhan.cinemasearch.core.data.Genre
import com.syhan.cinemasearch.core.data.trim
import com.syhan.cinemasearch.core.presentation.components.MovieImage
import com.syhan.cinemasearch.core.presentation.movie_details.components.MovieDetailsAppBar
import com.syhan.cinemasearch.core.presentation.movie_list.state.MovieItemState
import com.syhan.cinemasearch.core.presentation.theme.black
import com.syhan.cinemasearch.core.presentation.theme.grey
import com.syhan.cinemasearch.core.presentation.theme.navy
import com.syhan.cinemasearch.core.presentation.theme.white

private const val TAG = "MovieDetailsScreen"

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel,
    navigateUp: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MovieDetailsContent(
        state = state,
        navigateUp = navigateUp
    )
}

@Composable
fun MovieDetailsContent(
    state: MovieItemState,
    navigateUp: () -> Unit,
) {

    Scaffold(
        topBar = {
            MovieDetailsAppBar(
                title = state.name,
                navigateUp = navigateUp
            )
        },
        containerColor = white
    ) { contentPadding ->
        LazyColumn(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(contentPadding)
                .padding(horizontal = 12.dp)
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(Modifier.height(24.dp))
                    MovieImage(
                        modifier = Modifier
                            .height(230.dp)
                            .width(160.dp),
                        imageUrl = state.imageUrl
                    )
                }
            }
            item {
                Text(
                    text = state.localizedName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = black,
                    maxLines = Int.MAX_VALUE,
                )
            }
            item {
                val genres = state.genres
                    .toString()
                    .removePrefix("[")
                    .removeSuffix("]") + ", "
                val yearPublished = stringResource(R.string.year_published, state.year)
                Text(
                    text = if (state.genres.isEmpty()) yearPublished else genres + yearPublished,
                    fontSize = 16.sp,
                    color = grey,
                    maxLines = Int.MAX_VALUE,
                )
            }
            state.rating?.let {
                item {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "${it.trim()} ",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = navy
                        )
                        Text(
                            text = stringResource(R.string.kinopoisk),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = navy
                        )
                    }
                }
            }
            state.description?.let {
                item {
                    Text(
                        text = it,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = black
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun DetailsScreenPreview1() {
    MovieDetailsContent(
        state = MovieItemState(
            name = "Deltarune tomorrow",
            year = 2025,
            localizedName = "Мы забыли нанять переводчика",
            rating = 9.8432f,
            genres = Genre.entries.map { it.genreName },
            description = stringResource(R.string.placeholder_description)
        )
    ) { }
}

@Preview
@Composable
private fun DetailsScreenPreview2() {
    MovieDetailsContent(
        state = MovieItemState(
            name = "No movies today mate",
            year = 2025,
            localizedName = "Наш переводчик простудился",
            rating = null,
            genres = Genre.entries.map { it.genreName }.subList(0, 4),
            description = null
        )
    ) { }
}