package com.denisvieira05.githubusersearch.ui.modules.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.ui.components.CircularProgressLoading
import com.denisvieira05.githubusersearch.ui.modules.homescreen.components.SuggestedUserList
import com.denisvieira05.githubusersearch.ui.modules.homescreen.components.UserSearchSection

@Composable
fun HomeScreen(
    navigateToUserDetail: (userName: String) -> Unit,
    navigateToSuggestedUsers: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchText by viewModel.searchTextState.collectAsState()

    LaunchedEffect(key1 = true, block = {
        viewModel.fetchSuggestedUsers()
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .padding(dimensionResource(id = R.dimen.normal_space_size)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        UserSearchSection(
            onPressSearch = { navigateToUserDetail(searchText) },
            onSearchFieldChange = viewModel::updateSearchText
        )
        if (uiState.isLoading) {
            CircularProgressLoading(size = dimensionResource(id = R.dimen.circular_progress_loading_box))
        }

        if (uiState.suggestedUsers != null) {
            SuggestedUserList(
                uiState.suggestedUsers!!,
                onPressUserItem = { userName ->
                    navigateToUserDetail(userName)
                },
                onPressSeeAll = {
                    navigateToSuggestedUsers()
                }
            )
        }
    }
}
