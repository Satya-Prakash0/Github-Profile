package com.satya.profilesearchapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RepoEntity::class], version = 1, exportSchema = false)
abstract class RepoDatabase : RoomDatabase() {

    abstract fun repoDao(): RepoDao

    companion object {
        @Volatile
        private var INSTANCE: RepoDatabase? = null

        fun getInstance(context: Context): RepoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RepoDatabase::class.java,
                    "repo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}