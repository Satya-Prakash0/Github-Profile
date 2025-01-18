package com.satya.profilesearchapp.di

import com.satya.profilesearchapp.data.repository.GithubRepositoryImpl
import com.satya.profilesearchapp.domain.repository.GithubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt Module for providing data-related dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindGithubRepository(githubRepositoryImpl: GithubRepositoryImpl) : GithubRepository
}