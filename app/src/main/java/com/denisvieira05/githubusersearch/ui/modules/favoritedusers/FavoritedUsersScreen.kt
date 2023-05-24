package com.denisvieira05.githubusersearch.ui.modules.favoritedusers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.ui.components.AppTopBar
import com.denisvieira05.githubusersearch.ui.components.CircularProgressLoading
import com.denisvieira05.githubusersearch.ui.components.ErrorContent
import com.denisvieira05.githubusersearch.ui.modules.favoritedusers.FavoritedUsersScreenUIState.Loaded
import com.denisvieira05.githubusersearch.ui.modules.favoritedusers.FavoritedUsersScreenUIState.Loading
import com.denisvieira05.githubusersearch.ui.modules.favoritedusers.components.favoritedUsersLazyGrid
import com.denisvieira05.githubusersearch.ui.modules.homescreen.SuggestedUsersUIState

@OptIn(ExperimentalMaterial3Api::class)
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
                    favoritedUsersLazyGrid((uiState as Loaded).favoritedUsers) { userName ->
                        navigateToUserDetail(userName)
                    }
                }

                else -> {}
            }

        }

    }
}

