package com.satya.profilesearchapp.domain.repository

import com.satya.profilesearchapp.domain.model.RepoUiModel

/**
 * Handles repository data operations from network and local cache.
 */
interface GithubRepository {

    /**
     * Fetches a list of repositories.
     */
    suspend fun getRepositories(): List<RepoUiModel>

    /**
     * Searches repositories by a query.
     */
    suspend fun searchRepositories(query: String): List<RepoUiModel>
}
