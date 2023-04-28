package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisvieira05.githubusersearch.domain.model.Repository
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import com.denisvieira05.githubusersearch.domain.usecases.GetRepositoriesUseCase
import com.denisvieira05.githubusersearch.domain.usecases.GetUserDetailUseCase
import com.denisvieira05.githubusersearch.ui.main.navigation.NavArguments.USERNAME_NAV_ARGUMENT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    private val userName: String = checkNotNull(savedStateHandle[USERNAME_NAV_ARGUMENT])

    private val _uiState = MutableStateFlow(UserDetailUIState())
    val uiState = _uiState.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            fetchUserDetails()
            fetchUserRepositories()
        }
    }

    private suspend fun fetchUserDetails() {
        showUserLoading()
        getUserDetailUseCase(userName).collect {
            _uiState.value = uiState.value.copy(
                user = it,
                isLoadingUser = false
            )
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
