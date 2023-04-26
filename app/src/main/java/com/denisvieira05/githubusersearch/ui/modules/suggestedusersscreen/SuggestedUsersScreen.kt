package com.denisvieira05.githubusersearch.ui.modules.suggestedusersscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.ui.components.AppTopBar
import com.denisvieira05.githubusersearch.ui.components.CircularProgressLoading
import com.denisvieira05.githubusersearch.ui.modules.suggestedusersscreen.components.SuggestedUsersLazyGrid
import com.denisvieira05.githubusersearch.ui.navigation.ScreenRoutesBuilder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestedUsersScreen(
    navController: NavController,
    viewModel: SuggestedUsersScreenViewModel = hiltViewModel(),
) {
    val uiState by remember { viewModel.uiState }

    val suggestedUsers by remember {
        derivedStateOf { uiState.suggestedUsers }
    }
    val isLoading by remember {
        derivedStateOf { uiState.isLoading }
    }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchSuggestedUsers()
    })

    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(R.string.suggested_users_screen_title),
                onClickBack = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        Column {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressLoading(
                    size = dimensionResource(id = R.dimen.circular_progress_loading_box)
                )
            }
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
                if (isLoading) {
                    item {

                    }
                }
                if (suggestedUsers != null) {
                    SuggestedUsersLazyGrid(suggestedUsers!!) { userName ->
                        navController.navigate(
                            ScreenRoutesBuilder.buildRouteWithSimpleArgument(
                                ScreenRoutesBuilder.USER_DETAIL_SCREEN_ROUTE,
                                userName
                            )
                        )
                    }
                }
            }
        }


    }

}

