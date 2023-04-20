package com.denisvieira05.githubusersearch.ui.navigation

object ScreenRoutesBuilder {

    // Arguments
    const val USERNAME_NAV_ARGUMENT = "userName"

    // Routes
    const val HOME_SCREEN_ROUTE = "homeScreen"
    const val USER_DETAIL_SCREEN_ROUTE = "userDetailScreen"

    fun buildRouteWithSimpleArgument(route: String, userName: String) = "$route/$userName"
}