package com.syhan.cinemasearch.core.presentation.state

import androidx.compose.runtime.Immutable

@Immutable
sealed interface UiState {
    data object Loading : UiState
    data object Error : UiState
    data object Success : UiState
}