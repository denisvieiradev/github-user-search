package com.denisvieira05.githubusersearch.ui.modules.homescreen

import com.denisvieira05.githubusersearch.domain.model.SuggestedUser

sealed class HomeUIState {

    object Loading : HomeUIState()

    data class Loaded(val suggestedUsers: List<SuggestedUser>) : HomeUIState()

    object Error : HomeUIState()
}