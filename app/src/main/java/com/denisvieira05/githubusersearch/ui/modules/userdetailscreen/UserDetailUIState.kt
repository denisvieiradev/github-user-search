package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen

import com.denisvieira05.githubusersearch.domain.model.SuggestedUser

data class UserDetailUIState(
    val suggestedUsers: List<SuggestedUser>? = null,
    val isLoading: Boolean = false,
)