package com.satya.profilesearchapp.data.model

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("html_url")
    val repoURL: String,
    @SerializedName("avatar_url")
    val profileURL: String
)