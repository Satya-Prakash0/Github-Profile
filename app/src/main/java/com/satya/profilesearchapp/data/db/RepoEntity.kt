package com.satya.profilesearchapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repositories")
data class RepoEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val repoURL: String,
    val profileURL: String,
    val timestamp: Long = System.currentTimeMillis()
)