package com.denisvieira05.githubusersearch.ui.modules.favoritedusers

import com.denisvieira05.githubusersearch.domain.model.UserDetail

data class FavoritedUsersScreenUIState(
    val favoritedUsers: List<UserDetail>? = null,
    val isLoading: Boolean = false,
)