package com.denisvieira05.githubusersearch.ui.screens.userdetail

import com.denisvieira05.githubusersearch.domain.model.UserItemList

data class UserDetailUIState(
    val suggestedUsers: List<UserItemList>? = null,
    val isLoading: Boolean = false,
)