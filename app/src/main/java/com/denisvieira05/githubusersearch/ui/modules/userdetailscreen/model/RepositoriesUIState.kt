package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.model

import com.denisvieira05.githubusersearch.domain.model.Repository

sealed class RepositoriesUIState {

    object Loading : RepositoriesUIState()

    data class Loaded(val repositories: List<Repository>) : RepositoriesUIState()

    object Error : RepositoriesUIState()
}