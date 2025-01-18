package com.satya.profilesearchapp.util

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(val exception: Throwable) : NetworkResult<Nothing>()
    data object Loading : NetworkResult<Nothing>()

    companion object {
        fun <T> loading() = Loading
        fun <T> success(data: T) = Success(data)
        fun error(exception: Throwable) = Error(exception)
    }
}

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}