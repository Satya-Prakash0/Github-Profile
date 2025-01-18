package com.satya.profilesearchapp.data.repository


import com.satya.profilesearchapp.data.api.GitHubApi
import com.satya.profilesearchapp.data.db.RepoDao
import com.satya.profilesearchapp.domain.model.RepoUiModel
import com.satya.profilesearchapp.domain.repository.GithubRepository
import com.satya.profilesearchapp.util.toEntity
import com.satya.profilesearchapp.util.toUiModel
import javax.inject.Inject

/**
 * Implementation of GithubRepository.
 *
 * This class manages fetching repositories from both a remote API and a local database cache.
 *
 * @param api Instance of GitHubApi to make network requests.
 * @param dao Instance of RepoDao for local database operations.
 */
class GithubRepositoryImpl @Inject constructor(
    private val api: GitHubApi,
    private val dao: RepoDao
) : GithubRepository {

    /**
     * Fetches a list of repositories.
     *
     * - First checks the local database for cached data.
     * - If no cached data is available, fetches from the API, updates the cache, and returns the data.
     *
     * @return A list of RepoUiModel representing repositories.
     */
    override suspend fun getRepositories(): List<RepoUiModel> {
        return try {
            val cachedData = dao.getAllRepos() // Fetch cached repositories
            if (cachedData.isNotEmpty()) {
                return cachedData.map { it.toUiModel() }
            }

            // Fetch data from the API if cache is empty
            val response = api.getUserRepository()
            val repoEntities = response.items.map { it.toEntity() }
            val repoUiModels = repoEntities.map { it.toUiModel() }

            // Update the cache with the fetched data
            dao.clearAll()
            dao.insertAll(repoEntities)

            repoUiModels
        } catch (e: Exception) {
            throw e // Rethrow exceptions to be handled by the caller
        }
    }

    /**
     * Searches repositories based on a query.
     *
     * - Searches the local database using a LIKE query.
     *
     * @param query The search term.
     * @return A list of RepoUiModel representing the search results.
     */
    override suspend fun searchRepositories(query: String): List<RepoUiModel> {
        return try {
            dao.searchRepos("%$query%") // Search using a wildcard query
                .map { it.toUiModel() }
        } catch (e: Exception) {
            throw Exception("Failed to search repositories: ${e.message}")
        }
    }
}
