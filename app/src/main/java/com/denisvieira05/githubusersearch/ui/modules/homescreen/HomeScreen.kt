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
    val suggestedUsers by viewModel.suggestedUsers.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
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
            onPressSearch = { navigateToUserDetail(viewModel.searchText) },
            onSearchFieldChange = viewModel::updateSearchText
        )
        if (isLoading) {
            CircularProgressLoading(size = dimensionResource(id = R.dimen.circular_progress_loading_box))
        }

        if (suggestedUsers != null) {
            SuggestedUserList(
                suggestedUsers!!,
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
