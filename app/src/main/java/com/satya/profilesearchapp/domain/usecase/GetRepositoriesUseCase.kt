package com.satya.profilesearchapp.domain.usecase

import com.satya.profilesearchapp.domain.model.RepoUiModel
import com.satya.profilesearchapp.domain.repository.GithubRepository
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(private val repository: GithubRepository)  {

    suspend operator fun invoke(): List<RepoUiModel> {
        return repository.getRepositories()
    }

}