package com.denisvieira05.githubusersearch

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
import com.denisvieira05.githubusersearch.ui.navigation.ScreenRoute.SuggestedUsersScreenRoute
import com.denisvieira05.githubusersearch.ui.navigation.ScreenRoute.UserDetailScreenRoute

/**
 * Responsible for holding state related to [MainComposableAppState] and containing UI-related logic.
 */
@Stable
class MainComposableAppState(
    val navController: NavHostController,
    private val context: Context
) {

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
}


/**
 * Remembers and creates an instance of [MainComposableAppState]
 */
@Composable
fun rememberMainComposableAppState(
    navController: NavHostController = rememberNavController(),
    resources: Resources = resources(),
    context: Context = LocalContext.current
) =
    remember(navController, resources, context) {
        MainComposableAppState(navController, context)
    }


/**
 * A composable function that returns the [Resources]. It will be recomposed when `Configuration`
 * gets updated.
 */
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
