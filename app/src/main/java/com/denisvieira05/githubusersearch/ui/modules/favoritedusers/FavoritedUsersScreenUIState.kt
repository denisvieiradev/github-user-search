package com.denisvieira05.githubusersearch.ui.modules.favoritedusers

import com.denisvieira05.githubusersearch.domain.model.UserDetail

sealed class FavoritedUsersScreenUIState {

    object Loading : FavoritedUsersScreenUIState()

    data class Loaded(val favoritedUsers: List<UserDetail>) : FavoritedUsersScreenUIState()

    object Error : FavoritedUsersScreenUIState()
}