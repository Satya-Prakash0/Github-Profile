package com.satya.profilesearchapp.util

/**
 * A sealed class representing the different states of UI data.
 * It can represent the loading, success, or error state of data.
 */
sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}