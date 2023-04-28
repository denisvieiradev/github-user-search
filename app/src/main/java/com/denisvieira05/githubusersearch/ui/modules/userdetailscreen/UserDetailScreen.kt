package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.ui.components.AppTopBar
import com.denisvieira05.githubusersearch.ui.components.CircularProgressLoading
import com.denisvieira05.githubusersearch.ui.main.rememberMainComposableAppState
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.components.UserDetailHeader
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.components.UserRepositoriesList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    navigateToBack: () -> Unit,
    userName: String?,
    viewModel: UserDetailViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsState()
    val context = rememberMainComposableAppState().weakContext.get()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchData()
    })

    Scaffold(
        topBar = {
            AppTopBar(
                title = "@$userName",
                onClickBack = {
                    navigateToBack()
                },
                actions = {
                    IconButton(onClick = { context?.let { shareUser(userName, it) } }) {
                        Icon(Icons.Filled.Share, null)
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(
                    start = dimensionResource(id = R.dimen.medium_space_size),
                    end = dimensionResource(id = R.dimen.medium_space_size)
                ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.normal_space_size)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                if (uiState.isLoadingUser) {
                    CircularProgressLoading(
                        size = dimensionResource(id = R.dimen.circular_progress_loading_box)
                    )
                }
            }

            item {
                if (uiState.user != null) {
                    UserDetailHeader(uiState.user!!)
                }
            }

            item {
                if (uiState.isLoadingRepositories) {
                    CircularProgressLoading(
                        size = dimensionResource(id = R.dimen.circular_progress_loading_box)
                    )
                }
            }

            UserRepositoriesList(
                repositories = uiState.repositories
            ) {
                openUserRepositoryOnBrowser()
            }

        }
    }

}

fun openUserRepositoryOnBrowser() {

}

fun shareUser(userName: String?, context: Context) {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, context.getString(R.string.share_user_profile_text, userName))
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(intent, "Compartilhar via")
    startActivity(context, shareIntent, Bundle())
}