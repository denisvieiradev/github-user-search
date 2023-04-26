package com.denisvieira05.githubusersearch.ui.navigation

object ScreenRoutesBuilder {

    // Arguments
    const val USERNAME_NAV_ARGUMENT = "userName"
    const val SUGGESTED_USERS_NAV_ARGUMENT = "suggestedUsers"

    // Routes
    const val HOME_SCREEN_ROUTE = "homeScreen"
    const val USER_DETAIL_SCREEN_ROUTE = "userDetailScreen"
    const val SUGGESTED_USERS_SCREEN_ROUTE = "suggestedUsersScreen"

    fun buildRouteWithSimpleArgument(route: String, userName: String) = "$route/$userName"
}