package com.syhan.cinemasearch.core.data

sealed interface UiState {
    data object ShowLoading : UiState
    data object ShowNoConnection : UiState
    data object ShowError : UiState
    data object ShowContent : UiState
}