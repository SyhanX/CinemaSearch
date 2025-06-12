package com.syhan.cinemasearch.core.presentation.state

sealed interface UiState {
    data object Loading : UiState
    data object Error : UiState
    data object Success : UiState
}