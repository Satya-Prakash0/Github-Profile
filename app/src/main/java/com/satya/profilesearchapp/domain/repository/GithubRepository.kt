package com.satya.profilesearchapp.domain.repository

import com.satya.profilesearchapp.domain.model.RepoUiModel

interface GithubRepository {
    suspend fun getRepositories(): List<RepoUiModel>
    suspend fun searchRepositories(query: String): List<RepoUiModel>
}