package com.denisvieira05.githubusersearch

import androidx.compose.runtime.Composable
import com.denisvieira05.githubusersearch.ui.navigation.AppNavHost
import com.denisvieira05.githubusersearch.ui.theme.GithubUserSearchTheme


@Composable
fun MainComposableApp() {
    val appState = rememberMainComposableAppState()

    GithubUserSearchTheme {
        AppNavHost(appState)
    }
}
