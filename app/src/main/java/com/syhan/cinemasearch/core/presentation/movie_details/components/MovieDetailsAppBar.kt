package com.syhan.cinemasearch.core.presentation.movie_details.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syhan.cinemasearch.R
import com.syhan.cinemasearch.core.presentation.theme.navy
import com.syhan.cinemasearch.core.presentation.theme.white
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsAppBar(
    title: String,
    navigateUp: () -> Unit,
) {
    val tooltipState = rememberTooltipState(isPersistent = true)
    val scope = rememberCoroutineScope()
    var isTooltipRequired by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        title = {
            MovieNameTooltip(
                tooltipText = title,
                tooltipState = tooltipState
            ) {
                Surface(
                    enabled = isTooltipRequired,
                    onClick = {
                        scope.launch {
                            tooltipState.show()
                        }
                    },
                    color = navy,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        onTextLayout = {
                            isTooltipRequired = it.hasVisualOverflow
                        }
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(
                onClick = navigateUp
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