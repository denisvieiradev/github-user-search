package com.denisvieira05.githubusersearch.ui.modules.suggestedusersscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisvieira05.githubusersearch.domain.usecases.GetSuggestedUsersUseCase
import com.denisvieira05.githubusersearch.ui.modules.homescreen.SuggestedUsersUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestedUsersScreenViewModel @Inject constructor(
    private val getSuggestedUsersUseCase: GetSuggestedUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SuggestedUsersUIState?>(null)
    val uiState = _uiState.asStateFlow()

    private val errorHandler = CoroutineExceptionHandler { _, error ->
        _uiState.value = SuggestedUsersUIState.Error
    }

    fun fetchSuggestedUsers() {
        viewModelScope.launch(errorHandler) {
            _uiState.value = SuggestedUsersUIState.Loading

            getSuggestedUsersUseCase().collect { suggestedUsers ->
                _uiState.value = SuggestedUsersUIState.Loaded(suggestedUsers)
            }
        }
    }
}
