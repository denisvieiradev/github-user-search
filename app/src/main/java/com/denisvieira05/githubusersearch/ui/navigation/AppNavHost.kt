package com.denisvieira05.githubusersearch.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.denisvieira05.githubusersearch.ui.navigation.ScreenRoutesBuilder.HOME_SCREEN_ROUTE
import com.denisvieira05.githubusersearch.ui.modules.homescreen.HomeScreen

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
    }
}