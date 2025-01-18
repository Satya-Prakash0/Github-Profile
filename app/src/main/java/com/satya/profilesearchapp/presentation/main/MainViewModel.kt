package com.satya.profilesearchapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satya.profilesearchapp.domain.model.RepoUiModel
import com.satya.profilesearchapp.domain.usecase.GetRepositoriesUseCase
import com.satya.profilesearchapp.domain.usecase.SearchRepositoriesUseCase
import com.satya.profilesearchapp.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<List<RepoUiModel>>>()
    val uiState: LiveData<UiState<List<RepoUiModel>>> = _uiState

//    private val _searchQuery = MutableStateFlow("")
//    val searchQuery: StateFlow<String> = _searchQuery

    fun loadRepositories() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val repositories = getRepositoriesUseCase()
                _uiState.value = UiState.Success(repositories)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    fun searchRepository(query: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
             try {
                 val repositories = searchRepositoriesUseCase(query)
                 _uiState.value = UiState.Success(repositories)
             } catch (e: Exception) {
                 _uiState.value = UiState.Error(e.message ?: "Not Found")
             }
        }
    }

}