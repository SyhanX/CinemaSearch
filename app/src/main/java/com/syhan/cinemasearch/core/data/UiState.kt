package com.syhan.cinemasearch.core.data

import androidx.compose.runtime.Immutable

@Immutable
sealed interface UiState {
    data object ShowLoading : UiState
    data object ShowError : UiState
    data object ShowContent : UiState
}