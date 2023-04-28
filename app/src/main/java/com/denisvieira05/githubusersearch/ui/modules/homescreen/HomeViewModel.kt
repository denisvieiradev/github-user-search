package com.denisvieira05.githubusersearch.ui.modules.homescreen

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.domain.usecases.GetSuggestedUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSuggestedUsersUseCase: GetSuggestedUsersUseCase,
) : ViewModel() {

    private val _searchTextState = MutableStateFlow("")
    val searchTextState = _searchTextState.asStateFlow()

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    private val errorHandler = CoroutineExceptionHandler { _, error ->
        viewModelScope.launch {
            isLoading(false)
        }
    }

    fun updateSearchText(text: String) {
        _searchTextState.value = text
    }

    fun fetchSuggestedUsers() {
        viewModelScope.launch(errorHandler) {
            isLoading(true)
            getSuggestedUsersUseCase().collect { suggestedUsers ->
                onResult(suggestedUsers)
                isLoading(false)
            }
        }
    }

    private fun isLoading(isLoading: Boolean) {
        _uiState.value = _uiState.value.copy(
            isLoading = isLoading
        )
    }

    private fun onResult(users: List<SuggestedUser>) {
        this._uiState.value = this._uiState.value.copy(
            suggestedUsers = users
        )
    }
}
