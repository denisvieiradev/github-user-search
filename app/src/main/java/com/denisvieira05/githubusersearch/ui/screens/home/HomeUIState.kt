package com.denisvieira05.githubusersearch.ui.screens.home

import com.denisvieira05.githubusersearch.domain.model.UserItemList

data class HomeUIState(
    val suggestedUsers: List<UserItemList>? = null,
    val isLoading: Boolean = false,
)