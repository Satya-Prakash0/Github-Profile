package com.satya.profilesearchapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satya.profilesearchapp.domain.model.RepoUiModel
import com.satya.profilesearchapp.domain.usecase.GetRepositoriesUseCase
import com.satya.profilesearchapp.domain.usecase.SearchRepositoriesUseCase
import com.satya.profilesearchapp.util.ErrorHandler
import com.satya.profilesearchapp.util.Results
import com.satya.profilesearchapp.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    // LiveData for managing UI state
    private val _uiState = MutableLiveData<UiState<List<RepoUiModel>>>()
    val uiState: LiveData<UiState<List<RepoUiModel>>> = _uiState

    /**
     * Loads repositories using the getRepositoriesUseCase and updates the UI state.
     */
    fun loadRepositories() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            when (val result = getRepositoriesUseCase()) {
                is Results.Success -> _uiState.value = UiState.Success(result.data)
                is Results.Error -> _uiState.value = UiState.Error(
                    errorHandler.getErrorMessage(result.exception)
                )
            }
        }
    }

    /**
     * Searches repositories using the searchRepositoriesUseCase and updates the UI state.
     */
    fun searchRepository(query: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            when (val result = searchRepositoriesUseCase(query)) {
                is Results.Success -> _uiState.value = UiState.Success(result.data)
                is Results.Error -> _uiState.value = UiState.Error(
                    errorHandler.getErrorMessage(result.exception)
                )
            }
        }
    }

}