package com.syhan.cinemasearch.core.presentation.movie_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syhan.cinemasearch.R
import com.syhan.cinemasearch.core.presentation.movie_list.state.GenreItemState
import com.syhan.cinemasearch.core.presentation.movie_list.state.MovieItemState
import com.syhan.cinemasearch.core.presentation.theme.grey
import com.syhan.cinemasearch.core.presentation.theme.white

@Composable
fun ListOfGenresAndMovies(
    genres: List<GenreItemState>,
    movies: List<MovieItemState>,
    onMovieClick: (Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .background(color = white)
    ) {
        item(
            span = { GridItemSpan(maxCurrentLineSpan) }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
            ) {
                Text(
                    text = stringResource(R.string.genres),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    lineHeight = TextUnit(2f, TextUnitType.Em)
                )
            }
        }
        items(
            span = { GridItemSpan(maxCurrentLineSpan) },
            items = genres,
            key = { it.id }
        ) { genre ->
            GenreItem(
                name = genre.name,
                isSelected = genre.isSelected,
                onItemClick = {
                    genre.onClick(genre.id)
                }
            )
        }
        item(
            span = { GridItemSpan(maxCurrentLineSpan) }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
            ) {
                Text(
                    text = stringResource(R.string.films),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    lineHeight = TextUnit(2.5f, TextUnitType.Em)
                )
            }
        }
        if (movies.isEmpty()) {
            item(
                span = { GridItemSpan(maxCurrentLineSpan) }
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                ) {
                    Text(
                        text = stringResource(R.string.nothing_here),
                        fontSize = 18.sp,
                        lineHeight = TextUnit(2.5f, TextUnitType.Em),
                        color = grey
                    )
                }
            }
        } else {
            items(
                items = movies,
                key = { movie -> movie.id }
            ) { movie ->
                MovieListItem(
                    name = movie.localizedName,
                    imageUrl = movie.imageUrl,
                    onClick = { onMovieClick(movie.id) },
                    modifier = Modifier.animateItem()
                )
            }
        }
    }
}