package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisvieira05.githubusersearch.domain.model.DataOrException
import com.denisvieira05.githubusersearch.domain.model.Repository
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import com.denisvieira05.githubusersearch.domain.usecases.GetRepositoriesUseCase
import com.denisvieira05.githubusersearch.domain.usecases.GetUserDetailUseCase
import com.denisvieira05.githubusersearch.ui.navigation.ScreenRoutesBuilder.USERNAME_NAV_ARGUMENT
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

    private val _uiState = mutableStateOf(UserDetailUIState())
    val uiState: State<UserDetailUIState> = _uiState

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
        _uiState.value = _uiState.value.copy(
            isLoading = true
        )
    }

    private fun onLoaded() {
        _uiState.value = _uiState.value.copy(
            isLoading = false
        )
    }

    private fun onResult(userDetail: UserDetail?, repositories: List<Repository>?) {
        this._uiState.value = this._uiState.value.copy(
            user = userDetail,
            repositories = repositories,
        )
    }

}
