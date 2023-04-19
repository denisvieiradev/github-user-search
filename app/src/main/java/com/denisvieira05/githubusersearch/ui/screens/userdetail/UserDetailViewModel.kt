package com.denisvieira05.githubusersearch.ui.screens.userdetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = mutableStateOf(UserDetailUIState())
    val uiState: State<UserDetailUIState> = _uiState

}
