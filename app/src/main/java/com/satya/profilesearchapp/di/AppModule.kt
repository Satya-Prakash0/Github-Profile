package com.satya.profilesearchapp.di

import com.satya.profilesearchapp.data.repository.GithubRepositoryImpl
import com.satya.profilesearchapp.domain.repository.GithubRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindGithubRepository(githubRepositoryImpl: GithubRepositoryImpl) : GithubRepository
}