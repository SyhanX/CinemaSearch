package com.syhan.cinemasearch.core.presentation.state

import androidx.compose.runtime.Immutable

@Immutable
sealed interface UiState {
    data object ShowLoading : UiState
    data object ShowError : UiState
    data object ShowContent : UiState
}