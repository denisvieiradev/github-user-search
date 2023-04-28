package com.denisvieira05.githubusersearch.ui.modules.homescreen

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.domain.usecases.GetSuggestedUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSuggestedUsersUseCase: GetSuggestedUsersUseCase,
) : ViewModel() {

    private val _uiState = mutableStateOf(HomeUIState())
    val uiState
        get() = _uiState

    val suggestedUsers = derivedStateOf { uiState.value.suggestedUsers }
    val isLoading = derivedStateOf { uiState.value.isLoading }

    val _searchTextState = MutableStateFlow("")
    var searchText = _searchTextState.value

    fun updateSearchText(text: String) {
        searchText = text
    }

    fun fetchSuggestedUsers() {
        runServerCall(
            serverCall = {
                val result = getSuggestedUsersUseCase().data ?: emptyList()
                onResult(result)
                println("dns: onResult ${result.toString()} ")
            }
        )
    }

    private fun runServerCall(serverCall: suspend (() -> Unit)) {
        viewModelScope.launch {
            onLoading()
            serverCall()
        }.invokeOnCompletion {
            onLoaded()
        }
    }

    private fun onLoading() {
        _uiState.value = _uiState.value.copy(
            isLoading = true
        )
    }

    private fun onLoaded() {
        _uiState.value = _uiState.value.copy(
            isLoading = false
        )
    }

    private fun onResult(users: List<SuggestedUser>) {
        this._uiState.value = this._uiState.value.copy(
            suggestedUsers = users
        )
    }
}
