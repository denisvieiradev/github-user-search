package com.denisvieira05.githubusersearch.ui.main

import android.content.Context
import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.denisvieira05.githubusersearch.ui.main.navigation.ScreenRoute
import com.denisvieira05.githubusersearch.ui.main.navigation.ScreenRoute.FavoritedUsersScreenRoute
import com.denisvieira05.githubusersearch.ui.main.navigation.ScreenRoute.SuggestedUsersScreenRoute
import com.denisvieira05.githubusersearch.ui.main.navigation.ScreenRoute.UserDetailScreenRoute
import java.lang.ref.WeakReference

/**
 * Responsible for holding state related to [MainComposableAppState] and containing UI-related logic.
 */
@Stable
class MainComposableAppState(
    val navController: NavHostController,
    context: Context
) {
    val weakContext = WeakReference(context)

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun navigateBack() {
        navController.popBackStack()
    }

    fun upPress() {
        navController.navigateUp()
    }

    fun navigateToUserDetailScreen(userName: String) {
        navController.navigate(
            UserDetailScreenRoute.createRoute(userName)
        )
    }

    fun navigateToSuggestedUsersScreen() {
        navController.navigate(
            SuggestedUsersScreenRoute.route
        )
    }

    fun navigateToFavoritedUsersScreen() {
        navController.navigate(
            FavoritedUsersScreenRoute.route
        )
    }
}

/**
 * Remembers and creates an instance of [MainComposableAppState]
 */
@Composable
fun rememberMainComposableAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) =
    remember(navController, context) {
        MainComposableAppState(navController, context)
    }