package com.denisvieira05.githubusersearch.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.denisvieira05.githubusersearch.ui.modules.homescreen.HomeScreen
import com.denisvieira05.githubusersearch.ui.modules.suggestedusersscreen.SuggestedUsersScreen
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.UserDetailScreen
import com.denisvieira05.githubusersearch.ui.navigation.ScreenRoutesBuilder.HOME_SCREEN_ROUTE
import com.denisvieira05.githubusersearch.ui.navigation.ScreenRoutesBuilder.SUGGESTED_USERS_SCREEN_ROUTE
import com.denisvieira05.githubusersearch.ui.navigation.ScreenRoutesBuilder.USERNAME_NAV_ARGUMENT
import com.denisvieira05.githubusersearch.ui.navigation.ScreenRoutesBuilder.USER_DETAIL_SCREEN_ROUTE

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = HOME_SCREEN_ROUTE,
    ) {

        composable(
            route = HOME_SCREEN_ROUTE
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route = "$USER_DETAIL_SCREEN_ROUTE/{$USERNAME_NAV_ARGUMENT}",
            arguments = listOf(navArgument(USERNAME_NAV_ARGUMENT) { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString(USERNAME_NAV_ARGUMENT)
            UserDetailScreen(navController = navController, userName = userName)
        }

        composable(
            route = SUGGESTED_USERS_SCREEN_ROUTE
        ) {
            SuggestedUsersScreen(navController = navController)
        }
    }
}