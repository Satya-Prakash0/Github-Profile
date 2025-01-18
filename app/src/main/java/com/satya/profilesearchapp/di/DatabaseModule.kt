package com.satya.profilesearchapp.di

import android.content.Context
import androidx.room.Room
import com.satya.profilesearchapp.data.api.GitHubApi
import com.satya.profilesearchapp.data.db.RepoDao
import com.satya.profilesearchapp.data.db.RepoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RepoDatabase {
        return RepoDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideRepoDao(database: RepoDatabase): RepoDao {
        return database.repoDao()
    }
}