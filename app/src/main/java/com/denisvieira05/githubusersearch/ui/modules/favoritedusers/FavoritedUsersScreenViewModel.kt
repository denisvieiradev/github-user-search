package com.denisvieira05.githubusersearch.ui.modules.favoritedusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisvieira05.githubusersearch.domain.usecases.GetFavoritedUsersUseCase
import com.denisvieira05.githubusersearch.domain.usecases.GetSuggestedUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritedUsersScreenViewModel @Inject constructor(
    private val getFavoritedUsersUseCase: GetFavoritedUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritedUsersScreenUIState())
    val uiState: StateFlow<FavoritedUsersScreenUIState> = _uiState.asStateFlow()

    private val errorHandler = CoroutineExceptionHandler { _, error ->
        _uiState.value = uiState.value.copy(
            isLoading = false
        )
    }

    fun fetchFavoritedUsers() {
        showLoading()

        viewModelScope.launch(errorHandler) {
            getFavoritedUsersUseCase().collect {
                _uiState.value = uiState.value.copy(
                    favoritedUsers = it,
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
