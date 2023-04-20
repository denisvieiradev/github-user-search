package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen

import com.denisvieira05.githubusersearch.domain.model.Repository
import com.denisvieira05.githubusersearch.domain.model.UserDetail

data class UserDetailUIState(
    val user: UserDetail? = null,
    val repositories: List<Repository>? = null,
    val isLoading: Boolean = false,
)