package com.denisvieira05.githubusersearch.ui.modules.homescreen

import com.denisvieira05.githubusersearch.domain.model.SuggestedUser

sealed class SuggestedUsersUIState {

    object Loading : SuggestedUsersUIState()

    data class Loaded(val suggestedUsers: List<SuggestedUser>) : SuggestedUsersUIState()

    object Error : SuggestedUsersUIState()
}