package com.satya.profilesearchapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RepoDao {
    @Query("SELECT * FROM repositories ORDER BY timestamp DESC")
    suspend fun getAllRepos(): List<RepoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<RepoEntity>)

    @Query("DELETE FROM repositories")
    suspend fun clearAll()

    @Query("DELETE FROM repositories WHERE timestamp < :timestamp")
    suspend fun deleteOldCache(timestamp: Long)

    @Query("SELECT * FROM repositories WHERE name LIKE :query OR id LIKE :query")
    suspend fun searchRepos(query: String): List<RepoEntity>
}