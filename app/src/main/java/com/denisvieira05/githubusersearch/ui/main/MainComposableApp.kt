package com.denisvieira05.githubusersearch.ui.main

import androidx.compose.runtime.Composable
import com.denisvieira05.githubusersearch.ui.main.navigation.MainAppNavHost
import com.denisvieira05.githubusersearch.ui.theme.GithubUserSearchTheme


@Composable
fun MainComposableApp() {
    val appState = rememberMainComposableAppState()

    GithubUserSearchTheme {
        MainAppNavHost(appState)
    }
}
