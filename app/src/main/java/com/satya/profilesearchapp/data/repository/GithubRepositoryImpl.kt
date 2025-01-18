package com.satya.profilesearchapp.data.repository


import com.satya.profilesearchapp.data.api.GitHubApi
import com.satya.profilesearchapp.data.db.RepoDao
import com.satya.profilesearchapp.domain.model.RepoUiModel
import com.satya.profilesearchapp.domain.repository.GithubRepository
import com.satya.profilesearchapp.util.toEntity
import com.satya.profilesearchapp.util.toUiModel
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val api: GitHubApi,
    private val dao: RepoDao
) : GithubRepository {

    override suspend fun getRepositories(): List<RepoUiModel> {
        return try {
            val cachedData = dao.getAllRepos()
            if (cachedData.isNotEmpty()) {
                return cachedData.map { it.toUiModel() }
            }

            // If cache is empty, fetch from network
            val response = api.getUserRepository()

            val repoEntities = response.items.map { it.toEntity() }
            val repoUiModels: List<RepoUiModel> = repoEntities.map { it.toUiModel() }

            dao.clearAll()
            dao.insertAll(repoEntities)

            repoUiModels
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun searchRepositories(query: String): List<RepoUiModel> {
        return try {
            dao.searchRepos("%$query%")
                .map { it.toUiModel() }
        } catch (e: Exception) {
            throw Exception("Failed to search repositories: ${e.message}")
        }
    }
}
