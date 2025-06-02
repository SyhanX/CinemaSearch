package com.syhan.cinemasearch.core.presentation.movie_list.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.syhan.cinemasearch.R
import com.syhan.cinemasearch.core.presentation.theme.navy
import com.syhan.cinemasearch.core.presentation.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.films),
                fontSize = 20.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = navy,
            titleContentColor = white
        ),
    )
}