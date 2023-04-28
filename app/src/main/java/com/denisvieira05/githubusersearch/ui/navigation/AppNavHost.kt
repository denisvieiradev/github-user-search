package com.denisvieira05.githubusersearch.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.denisvieira05.githubusersearch.MainComposableAppState
import com.denisvieira05.githubusersearch.rememberMainComposableAppState
import com.denisvieira05.githubusersearch.ui.modules.homescreen.HomeScreen
import com.denisvieira05.githubusersearch.ui.modules.suggestedusersscreen.SuggestedUsersScreen
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.UserDetailScreen
import com.denisvieira05.githubusersearch.ui.navigation.NavArguments.USERNAME_NAV_ARGUMENT
import com.denisvieira05.githubusersearch.ui.navigation.ScreenRoute.HomeScreenRoute
import com.denisvieira05.githubusersearch.ui.navigation.ScreenRoute.SuggestedUsersScreenRoute
import com.denisvieira05.githubusersearch.ui.navigation.ScreenRoute.UserDetailScreenRoute

@Composable
fun AppNavHost(
    appState: MainComposableAppState = rememberMainComposableAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = HomeScreenRoute.route,
    ) {
        composable(
            route = HomeScreenRoute.route
        ) {
            HomeScreen(
                navigateToUserDetail = appState::navigateToUserDetailScreen,
                navigateToSuggestedUsers = appState::navigateToSuggestedUsersScreen
            )
        }

        composable(
            route = UserDetailScreenRoute.route,
            arguments = listOf(navArgument(USERNAME_NAV_ARGUMENT) { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString(USERNAME_NAV_ARGUMENT)

            UserDetailScreen(navigateToBack = appState::navigateBack, userName = userName)
        }

        composable(
            route = SuggestedUsersScreenRoute.route
        ) {
            SuggestedUsersScreen(
                navigateToBack = appState::navigateBack,
                navigateToUserDetail = appState::navigateToUserDetailScreen
            )
        }
    }
}