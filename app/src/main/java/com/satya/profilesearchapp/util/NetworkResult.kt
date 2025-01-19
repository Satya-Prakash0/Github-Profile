package com.satya.profilesearchapp.util

/**
 * A wrapper class to handle success and error cases.
 *
 * @param T The type of data in case of success
 */
sealed class Results<out T> {
    data class Success<out T>(val data: T) : Results<T>()
    data class Error(val exception: Exception) : Results<Nothing>()
}