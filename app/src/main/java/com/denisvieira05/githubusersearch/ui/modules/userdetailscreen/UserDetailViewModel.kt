package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisvieira05.githubusersearch.domain.usecases.CheckWasFavoritedUserUseCase
import com.denisvieira05.githubusersearch.domain.usecases.FavoritedActions
import com.denisvieira05.githubusersearch.domain.usecases.FavoritedActions.REMOVED_FAVORITE
import com.denisvieira05.githubusersearch.domain.usecases.FavoritedActions.SAVE_FAVORITE
import com.denisvieira05.githubusersearch.domain.usecases.GetRepositoriesUseCase
import com.denisvieira05.githubusersearch.domain.usecases.GetUserDetailUseCase
import com.denisvieira05.githubusersearch.domain.usecases.ToggleFavoritedUserUseCase
import com.denisvieira05.githubusersearch.ui.main.navigation.NavArguments.USERNAME_NAV_ARGUMENT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val toggleFavoritedUserUseCase: ToggleFavoritedUserUseCase,
    private val checkWasFavoritedUserUseCase: CheckWasFavoritedUserUseCase
) : ViewModel() {

    private val userName: String by lazy { checkNotNull(savedStateHandle[USERNAME_NAV_ARGUMENT]) }

    private val _uiState = MutableStateFlow(UserDetailUIState())
    val uiState = _uiState.asStateFlow()

    private val errorHandler = CoroutineExceptionHandler { _, error ->
//        isLoading(false)
    }

    fun fetchData() {
        viewModelScope.launch {
            fetchUserDetails()
            fetchUserRepositories()
        }
    }

    fun toggleFavoritedUser() {
        val currentUser = uiState.value.user
        val isFavoritedUserCurrently = uiState.value.isFavoritedUser

        if (currentUser != null && isFavoritedUserCurrently != null) {
            viewModelScope.launch {
                toggleFavoritedUserUseCase.invoke(
                    currentUser,
                    isFavorited = isFavoritedUserCurrently
                ).collect {
                    when (it) {
                        SAVE_FAVORITE -> {
                            _uiState.value = uiState.value.copy(
                                isFavoritedUser = true
                            )
                        }

                        REMOVED_FAVORITE -> {
                            _uiState.value = uiState.value.copy(
                                isFavoritedUser = false
                            )
                        }
                    }
                }
            }
        }
    }

    private suspend fun fetchUserDetails() {
        showUserLoading()
        getUserDetailUseCase(userName).collect {
            _uiState.value = uiState.value.copy(
                user = it,
                isLoadingUser = false
            )

            checkWasFavoritedUser(remoteId = it.id)
        }
    }

    private suspend fun fetchUserRepositories() {
        showRepositoriesLoading()
        getRepositoriesUseCase(userName).collect {
            _uiState.value = uiState.value.copy(
                repositories = it,
                isLoadingRepositories = false
            )
        }
    }

    private suspend fun checkWasFavoritedUser(remoteId: Long) {
        checkWasFavoritedUserUseCase.invoke(remoteId).collect {
            _uiState.value = uiState.value.copy(
                isFavoritedUser = it
            )
        }
    }

    private fun showUserLoading() {
        _uiState.value = uiState.value.copy(
            isLoadingUser = true
        )
    }

    private fun showRepositoriesLoading() {
        _uiState.value = uiState.value.copy(
            isLoadingRepositories = true
        )
    }
}
