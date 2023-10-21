package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.domain.model.Repository
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import com.denisvieira05.githubusersearch.ui.components.AppTopBar
import com.denisvieira05.githubusersearch.ui.components.CircularProgressLoading
import com.denisvieira05.githubusersearch.ui.components.ErrorContent
import com.denisvieira05.githubusersearch.ui.main.rememberMainComposableAppState
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.components.UserDetailHeader
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.components.UserRepositoriesList
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.model.RepositoriesUIState
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.model.UserDetailUIState


@Composable
fun UserDetailScreen(
    navigateToBack: () -> Unit,
    userName: String?,
    viewModel: UserDetailViewModel = hiltViewModel(),
) {

    val repositoriesUiState by viewModel.repositoriesUiState.collectAsState()
    val userDetailUiState by viewModel.userDetailUiState.collectAsState()
    val isFavoriteUiState by viewModel.isFavoriteUiState.collectAsState()

    LaunchedEffect(key1 = true, block = {
        viewModel.fetchData()
    })

    UserDetailScreenContent(
        navigateToBack,
        userName,
        repositoriesUiState,
        userDetailUiState,
        isFavoriteUiState,
    ) {
        viewModel.toggleFavoritedUser()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreenContent(
    navigateToBack: () -> Unit,
    userName: String?,
    repositoriesUiState: RepositoriesUIState?,
    userDetailUiState: UserDetailUIState?,
    isFavoriteUiState: Boolean?,
    onToggleFavoritedUser: () -> Unit
) {
    val context = rememberMainComposableAppState().weakContext.get()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "@$userName",
                onClickBack = {
                    navigateToBack()
                },
                actions = {
                    if (isFavoriteUiState != null) {
                        IconButton(onClick = {
                            onToggleFavoritedUser()
                        }) {
                            if (isFavoriteUiState) {
                                Icon(Icons.Filled.Favorite, null)
                            } else {
                                Icon(Icons.Filled.FavoriteBorder, null)
                            }
                        }
                    }

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
                when (userDetailUiState) {
                    UserDetailUIState.Loading -> {
                        CircularProgressLoading(
                            size = dimensionResource(id = R.dimen.circular_progress_loading_box)
                        )
                    }

                    UserDetailUIState.Error -> ErrorContent()
                    is UserDetailUIState.Loaded -> {
                        val user = userDetailUiState.user
                        UserDetailHeader(user)
                    }

                    else -> {}
                }
            }

            when (repositoriesUiState) {
                RepositoriesUIState.Loading -> {
                    item {
                        CircularProgressLoading(
                            size = dimensionResource(id = R.dimen.circular_progress_loading_box)
                        )
                    }
                }

                RepositoriesUIState.Error -> item { ErrorContent() }
                is RepositoriesUIState.Loaded -> {
                    val repositories =
                        repositoriesUiState.repositories
                    UserRepositoriesList(
                        repositories = repositories
                    ) { userUrl ->
                        openUserRepositoryOnBrowser(userUrl, context)
                    }
                }

                else -> {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserDetailScreenPreview() {
    val repositoryUIState = RepositoriesUIState.Loaded(
        listOf(
            Repository(
                id = 1L,
                name = "repository 1",
                description = "description 1",
                htmlUrl = "html 1",
                forks = 1,
                language = "pt-BR",
                stars = 20L
            )
        )
    )

    val userDetailUiState = UserDetailUIState.Loaded(
        UserDetail(
            id = 12312,
            completeName = "Name",
            userName = "repository description",
            avatarUrl = "",
            htmlUrl = "",
            followersCount = 123,
            followingCount = 123,
            repositoriesCount = 123,
            blog = "",
            bio = "3123",
            twitterUsername = "3123",
        )
    )

    UserDetailScreenContent(
        navigateToBack = { /*TODO*/ },
        userName = "username 1",
        repositoriesUiState = repositoryUIState,
        userDetailUiState = userDetailUiState,
        isFavoriteUiState = true
    ) {

    }
}

fun openUserRepositoryOnBrowser(userUrl: String, context: Context?) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(userUrl))
    context?.let { startActivity(it, browserIntent, Bundle()) }
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