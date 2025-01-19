package com.satya.profilesearchapp.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import com.satya.profilesearchapp.R

class ErrorHandler @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getErrorMessage(exception: Exception): String {
        return when (exception) {
            is IOException -> context.getString(R.string.error_network)
            is HttpException -> context.getString(R.string.error_server)
            else -> context.getString(R.string.error_generic)
        }
    }
}