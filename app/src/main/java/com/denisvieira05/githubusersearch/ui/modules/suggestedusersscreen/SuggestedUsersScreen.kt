package com.denisvieira05.githubusersearch.ui.modules.suggestedusersscreen

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
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.ui.components.AppTopBar
import com.denisvieira05.githubusersearch.ui.components.CircularProgressLoading
import com.denisvieira05.githubusersearch.ui.modules.homescreen.SuggestedUsersUIState
import com.denisvieira05.githubusersearch.ui.modules.suggestedusersscreen.components.suggestedUsersLazyGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestedUsersScreen(
    navigateToUserDetail: (userName: String) -> Unit,
    navigateToBack: () -> Unit,
    viewModel: SuggestedUsersScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchSuggestedUsers()
    })

    SuggestedUsersContent(
        navigateToUserDetail,
        navigateToBack,
        uiState
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestedUsersContent(
    navigateToUserDetail: (userName: String) -> Unit,
    navigateToBack: () -> Unit,
    uiState: SuggestedUsersUIState?
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.suggested_users_screen_title),
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
                SuggestedUsersUIState.Loading -> {
                    item {
                        CircularProgressLoading(
                            size = dimensionResource(id = R.dimen.circular_progress_loading_box)
                        )
                    }
                }

                is SuggestedUsersUIState.Loaded -> {
                    suggestedUsersLazyGrid(uiState.suggestedUsers) { userName ->
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
fun SuggestedUsersPreview() {
    val list = listOf(
        SuggestedUser(
            id = 1,
            userName = "mojombo",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
        ),
        SuggestedUser(
            id = 2,
            userName = "defunkt",
            avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4"
        ),
        SuggestedUser(
            id = 1,
            userName = "pjhyett",
            avatarUrl = "https://avatars.githubusercontent.com/u/3?v=4"
        )
    )

    val uiState = SuggestedUsersUIState.Loaded(list)

    SuggestedUsersContent(
        navigateToUserDetail = {},
        navigateToBack = { },
        uiState = uiState
    )
}

