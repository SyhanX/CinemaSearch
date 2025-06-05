package com.syhan.cinemasearch.core.presentation.movie_details.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syhan.cinemasearch.core.presentation.theme.darkGrey
import com.syhan.cinemasearch.core.presentation.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieNameTooltip(
    tooltipText: String,
    tooltipState: TooltipState,
    content: @Composable (() -> Unit),
) {
    TooltipBox(
        positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
        enableUserInput = false,
        tooltip = {
            RichTooltip(
                shape = RoundedCornerShape(8.dp),
                colors = TooltipDefaults.richTooltipColors(
                    containerColor = darkGrey,
                    contentColor = white
                ),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = tooltipText,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(vertical = 4.dp),
                )
            }
        },
        state = tooltipState,
    ) { content() }
}