package com.denisvieira05.githubusersearch.ui.modules.suggestedusersscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.domain.usecases.GetSuggestedUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestedUsersScreenViewModel @Inject constructor(
    private val getSuggestedUsersUseCase: GetSuggestedUsersUseCase
) : ViewModel() {

    private val _uiState = mutableStateOf(SuggestedUsersScreenUIState())
    val uiState: State<SuggestedUsersScreenUIState> = _uiState

    val suggestedUsers = derivedStateOf { uiState.value.suggestedUsers }
    val isLoading = derivedStateOf { uiState.value.isLoading }

    fun fetchSuggestedUsers() {
        runServerCall(
            serverCall = {
                val result = getSuggestedUsersUseCase().data ?: emptyList()
                onResult(result)
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
