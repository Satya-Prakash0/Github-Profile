package com.satya.profilesearchapp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: Owner,
)