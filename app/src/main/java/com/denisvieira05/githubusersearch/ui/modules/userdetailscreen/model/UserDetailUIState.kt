package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.model

import com.denisvieira05.githubusersearch.domain.model.UserDetail

sealed class UserDetailUIState {

    object Loading : UserDetailUIState()

    data class Loaded(val user: UserDetail) : UserDetailUIState()

    object Error : UserDetailUIState()
}
