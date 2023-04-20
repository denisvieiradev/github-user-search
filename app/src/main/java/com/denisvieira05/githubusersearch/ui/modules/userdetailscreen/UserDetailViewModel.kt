package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.denisvieira05.githubusersearch.domain.usecases.GetRepositoriesUseCase
import com.denisvieira05.githubusersearch.domain.usecases.GetUserDetailUseCase
import com.denisvieira05.githubusersearch.ui.navigation.ScreenRoutesBuilder.USERNAME_NAV_ARGUMENT
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun teste() {
        println("teste: $userName")
    }

}
