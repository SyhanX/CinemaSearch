package com.syhan.cinemasearch.core.presentation.movie_details.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.syhan.cinemasearch.R
import com.syhan.cinemasearch.core.presentation.theme.navy
import com.syhan.cinemasearch.core.presentation.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsAppBar(
    title: String,
    onNavigate: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Clip
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigate
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back),
                    contentDescription = stringResource(R.string.back)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = navy,
            titleContentColor = white,
            navigationIconContentColor = white
        ),
    )
}

@Preview
@Composable
private fun DetailsAppBarPreview() {
    MovieDetailsAppBar(
        title = "Among us"
    ) { }
}