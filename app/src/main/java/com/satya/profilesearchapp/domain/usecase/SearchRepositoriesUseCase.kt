package com.satya.profilesearchapp.domain.usecase

import com.satya.profilesearchapp.domain.model.RepoUiModel
import com.satya.profilesearchapp.domain.repository.GithubRepository
import com.satya.profilesearchapp.util.Results
import javax.inject.Inject

class SearchRepositoriesUseCase @Inject constructor(private val repository: GithubRepository) {

    suspend operator fun invoke(query: String): Results<List<RepoUiModel>> {
        return try {
            val repositories = repository.searchRepositories(query)
            Results.Success(repositories)
        } catch (e: Exception) {
            Results.Error(e)
        }
    }

}