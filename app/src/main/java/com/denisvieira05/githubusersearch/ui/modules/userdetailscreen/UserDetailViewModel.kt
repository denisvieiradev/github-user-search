package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisvieira05.githubusersearch.domain.usecases.CheckWasFavoritedUserUseCase
import com.denisvieira05.githubusersearch.domain.usecases.FavoritedActions.REMOVED_FAVORITE
import com.denisvieira05.githubusersearch.domain.usecases.FavoritedActions.SAVE_FAVORITE
import com.denisvieira05.githubusersearch.domain.usecases.GetRepositoriesUseCase
import com.denisvieira05.githubusersearch.domain.usecases.GetUserDetailUseCase
import com.denisvieira05.githubusersearch.domain.usecases.ToggleFavoritedUserUseCase
import com.denisvieira05.githubusersearch.ui.main.navigation.NavArguments.USERNAME_NAV_ARGUMENT
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.model.RepositoriesUIState
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.model.UserDetailErrorSourceEnum
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.model.UserDetailErrorSourceEnum.FETCH_REPOSITORIES
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.model.UserDetailErrorSourceEnum.TOGGLE_FAVORITED
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.model.UserDetailErrorSourceEnum.FETCH_USER_DETAILS
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.model.UserDetailUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _userDetailUiState = MutableStateFlow<UserDetailUIState?>(null)
    val userDetailUiState = _userDetailUiState.asStateFlow()

    private val _repositoriesUiState = MutableStateFlow<RepositoriesUIState?>(null)
    val repositoriesUiState = _repositoriesUiState.asStateFlow()

    private val _isFavoriteUiState = MutableStateFlow<Boolean?>(null)
    val isFavoriteUiState = _isFavoriteUiState.asStateFlow()

    fun fetchData() {
        viewModelScope.launch(getErrorHandler(FETCH_USER_DETAILS)) {
            fetchUserDetails()
        }
        viewModelScope.launch(getErrorHandler(FETCH_REPOSITORIES)) {
            fetchRepositories()
        }
    }

    fun toggleFavoritedUser() {
        val currentUser = (userDetailUiState.value as UserDetailUIState.Loaded).user
        val isFavoritedUserCurrently = isFavoriteUiState.value

        if (currentUser != null && isFavoritedUserCurrently != null) {
            viewModelScope.launch(getErrorHandler(TOGGLE_FAVORITED)) {
                toggleFavoritedUserUseCase.invoke(
                    currentUser,
                    isFavorited = isFavoritedUserCurrently
                ).collect {
                    when (it) {
                        SAVE_FAVORITE -> {
                            _isFavoriteUiState.value = true
                        }

                        REMOVED_FAVORITE -> {
                            _isFavoriteUiState.value = false

                        }
                    }
                }
            }
        }
    }

    private suspend fun fetchUserDetails() {
        _userDetailUiState.value = UserDetailUIState.Loading

        getUserDetailUseCase(userName).collect {
            _userDetailUiState.value = UserDetailUIState.Loaded(it)

            checkWasFavoritedUser(remoteId = it.id)
        }
    }

    private suspend fun fetchRepositories() {
        _repositoriesUiState.value = RepositoriesUIState.Loading

        getRepositoriesUseCase(userName).collect {
            _repositoriesUiState.value = RepositoriesUIState.Loaded(it)
        }
    }

    private suspend fun checkWasFavoritedUser(remoteId: Long) {
        checkWasFavoritedUserUseCase.invoke(remoteId).collect {
            _isFavoriteUiState.value = it
        }
    }

    private fun getErrorHandler(errorSource: UserDetailErrorSourceEnum) =
        CoroutineExceptionHandler { _, error ->
            when(errorSource) {
                FETCH_REPOSITORIES -> _repositoriesUiState.value = RepositoriesUIState.Error
                FETCH_USER_DETAILS -> _userDetailUiState.value = UserDetailUIState.Error
                TOGGLE_FAVORITED -> {}
                else -> {}
            }
        }

}
