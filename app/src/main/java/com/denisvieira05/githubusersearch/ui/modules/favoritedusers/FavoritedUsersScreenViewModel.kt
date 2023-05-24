package com.denisvieira05.githubusersearch.ui.modules.favoritedusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisvieira05.githubusersearch.domain.usecases.GetFavoritedUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritedUsersScreenViewModel @Inject constructor(
    private val getFavoritedUsersUseCase: GetFavoritedUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<FavoritedUsersScreenUIState?>(null)
    val uiState = _uiState.asStateFlow()

    private val errorHandler = CoroutineExceptionHandler { _, error ->
        _uiState.value = FavoritedUsersScreenUIState.Error
    }

    fun fetchFavoritedUsers() {
        _uiState.value = FavoritedUsersScreenUIState.Loading

        viewModelScope.launch(errorHandler) {
            getFavoritedUsersUseCase().collect {
                _uiState.value = FavoritedUsersScreenUIState.Loaded(it)
            }
        }
    }
}
