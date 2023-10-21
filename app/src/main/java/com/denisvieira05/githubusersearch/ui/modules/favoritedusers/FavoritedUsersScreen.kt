package com.denisvieira05.githubusersearch.ui.modules.favoritedusers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import com.denisvieira05.githubusersearch.ui.components.AppTopBar
import com.denisvieira05.githubusersearch.ui.components.CircularProgressLoading
import com.denisvieira05.githubusersearch.ui.components.ErrorContent
import com.denisvieira05.githubusersearch.ui.modules.favoritedusers.FavoritedUsersScreenUIState.Loaded
import com.denisvieira05.githubusersearch.ui.modules.favoritedusers.FavoritedUsersScreenUIState.Loading
import com.denisvieira05.githubusersearch.ui.modules.favoritedusers.components.favoritedUsersLazyGrid

@Composable
fun FavoritedUsersScreen(
    navigateToUserDetail: (userName: String) -> Unit,
    navigateToBack: () -> Unit,
    viewModel: FavoritedUsersScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchFavoritedUsers()
    })

    FavouriteUserContent(
        navigateToUserDetail,
        navigateToBack,
        uiState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteUserContent(
    navigateToUserDetail: (userName: String) -> Unit,
    navigateToBack: () -> Unit,
    uiState: FavoritedUsersScreenUIState?
) {

    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.favorited_users_screen_title),
                onClickBack = {
                    navigateToBack()
                }
            )
        }
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .padding(it)
                .padding(
                    start = dimensionResource(id = R.dimen.medium_space_size),
                    end = dimensionResource(id = R.dimen.medium_space_size)
                ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.normal_space_size)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.normal_space_size)),
            columns = GridCells.Fixed(2),
        ) {

            when (uiState) {
                Loading -> {
                    item {
                        CircularProgressLoading(
                            size = dimensionResource(id = R.dimen.circular_progress_loading_box)
                        )
                    }
                }

                is Error -> item { ErrorContent() }
                is Loaded -> {
                    favoritedUsersLazyGrid(uiState.favoritedUsers) { userName ->
                        navigateToUserDetail(userName)
                    }
                }

                else -> {}
            }

        }

    }
}


@Preview(showBackground = true)
@Composable
fun FavouriteUserPreview() {
    val list = listOf(
        UserDetail(
            id = 1,
            completeName = "full name 1",
            avatarUrl = "http",
            userName = "username 1",
            htmlUrl = "url",
            followersCount = 1L,
            followingCount = 1L,
            repositoriesCount = 1,
            blog = "dd",
            bio = "ok",
            twitterUsername = "fsf"
        ),
        UserDetail(
            id = 1,
            completeName = "full name 2",
            avatarUrl = "http",
            userName = "username 2",
            htmlUrl = "url",
            followersCount = 1L,
            followingCount = 1L,
            repositoriesCount = 1,
            blog = "dd",
            bio = "ok",
            twitterUsername = "fsf"
        )
    )

    val uiState = Loaded(list)

    FavouriteUserContent(
        navigateToUserDetail = {},
        navigateToBack = { /*TODO*/ },
        uiState = uiState
    )

}
