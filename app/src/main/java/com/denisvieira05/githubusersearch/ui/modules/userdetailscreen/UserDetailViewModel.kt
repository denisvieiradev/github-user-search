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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    private val userName: String = checkNotNull(savedStateHandle[USERNAME_NAV_ARGUMENT])

    private val uiState = mutableStateOf(UserDetailUIState())

    val user = derivedStateOf { uiState.value.user }
    val repositories = derivedStateOf { uiState.value.repositories }
    val isLoading = derivedStateOf { uiState.value.isLoading }

    fun fetchData() {
        runServerCall(
            serverCall = {
                val userDetail = getUserDetailUseCase(userName).data
                val repositories = getRepositoriesUseCase(userName).data
                onResult(userDetail, repositories)
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
        uiState.value = uiState.value.copy(
            isLoading = true
        )
    }

    private fun onLoaded() {
        uiState.value = uiState.value.copy(
            isLoading = false
        )
    }

    private fun onResult(userDetail: UserDetail?, repositories: List<Repository>?) {
        this.uiState.value = this.uiState.value.copy(
            user = userDetail,
            repositories = repositories,
        )
    }

}
