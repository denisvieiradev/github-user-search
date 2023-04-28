package com.denisvieira05.githubusersearch.ui.main.navigation

import com.denisvieira05.githubusersearch.ui.main.navigation.NavArguments.USERNAME_NAV_ARGUMENT


object NavArguments {
    const val USERNAME_NAV_ARGUMENT = "userName"
}

sealed class ScreenRoute(val route: String) {

    object HomeScreenRoute : ScreenRoute("home")
    object UserDetailScreenRoute : ScreenRoute("userDetail/{$USERNAME_NAV_ARGUMENT}") {
        fun createRoute(userName: String) = "userDetail/$userName"
    }

    object SuggestedUsersScreenRoute : ScreenRoute("suggestedUsers")
}