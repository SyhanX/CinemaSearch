package com.syhan.cinemasearch.core.domain.util

sealed interface NetworkResult<T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Error<T>(val type: NetworkError) : NetworkResult<T>
    data class Exception<T>(val exception: Throwable) : NetworkResult<T>
}

sealed interface NetworkError {
    data object NoInternet : NetworkError
    data object EmptyHttpBody : NetworkError
    data class UnexpectedHttpResponse(val code: Int) : NetworkError
}

class EmptyHttpBodyException(
    msg: String = "Http body is empty"
) : Exception(msg)