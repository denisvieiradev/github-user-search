package com.denisvieira05.githubusersearch.ui.modules.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisvieira05.githubusersearch.domain.usecases.GetSuggestedUsersUseCase
import com.denisvieira05.githubusersearch.ui.modules.homescreen.SuggestedUsersUIState.Error
import com.denisvieira05.githubusersearch.ui.modules.homescreen.SuggestedUsersUIState.Loaded
import com.denisvieira05.githubusersearch.ui.modules.homescreen.SuggestedUsersUIState.Loading
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

    private val _uiState = MutableStateFlow<SuggestedUsersUIState?>(null)
    val uiState = _uiState.asStateFlow()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.value = Error
    }

    fun fetchSuggestedUsers() {
        viewModelScope.launch(errorHandler) {
            _uiState.value = Loading

            getSuggestedUsersUseCase().collect { suggestedUsers ->
                _uiState.value = Loaded(suggestedUsers)
            }
        }
    }
}
