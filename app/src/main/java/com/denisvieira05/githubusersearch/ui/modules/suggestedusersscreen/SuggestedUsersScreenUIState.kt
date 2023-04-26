package com.denisvieira05.githubusersearch.ui.modules.suggestedusersscreen

import com.denisvieira05.githubusersearch.domain.model.SuggestedUser

data class SuggestedUsersScreenUIState(
    val suggestedUsers: List<SuggestedUser>? = null,
    val isLoading: Boolean = false,
)