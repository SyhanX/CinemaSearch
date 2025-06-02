package com.syhan.cinemasearch.core.presentation.movie_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syhan.cinemasearch.core.presentation.theme.darkYellow
import com.syhan.cinemasearch.core.presentation.theme.white

@Composable
fun GenreItem(
    name: String,
    isSelected: Boolean = false,
    onItemClick: () -> Unit = {}
) {
    Surface(
        onClick = onItemClick,
        color = if (isSelected) darkYellow else white,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            lineHeight = TextUnit(2f, TextUnitType.Em),
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        )
    }
}