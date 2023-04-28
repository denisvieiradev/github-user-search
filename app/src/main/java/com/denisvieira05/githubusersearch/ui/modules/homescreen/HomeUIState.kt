package com.denisvieira05.githubusersearch.ui.modules.homescreen

import com.denisvieira05.githubusersearch.domain.model.SuggestedUser

data class HomeUIState(
    val suggestedUsers: List<SuggestedUser>? = null,
    val isLoading: Boolean = false,
    val onError: Exception? = null,
)