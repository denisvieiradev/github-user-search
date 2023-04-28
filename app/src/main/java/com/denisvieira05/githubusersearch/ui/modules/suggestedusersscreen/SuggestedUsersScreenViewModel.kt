package com.denisvieira05.githubusersearch.ui.modules.suggestedusersscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisvieira05.githubusersearch.domain.usecases.GetSuggestedUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestedUsersScreenViewModel @Inject constructor(
    private val getSuggestedUsersUseCase: GetSuggestedUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SuggestedUsersScreenUIState())
    val uiState: StateFlow<SuggestedUsersScreenUIState> = _uiState.asStateFlow()

    fun fetchSuggestedUsers() {
        showLoading()

        viewModelScope.launch {
            getSuggestedUsersUseCase().collect {
                _uiState.value = uiState.value.copy(
                    suggestedUsers = it,
                    isLoading = false
                )
            }
        }
    }

    private fun showLoading() {
        _uiState.value = _uiState.value.copy(
            isLoading = true
        )
    }
}
