package com.denisvieira05.githubusersearch.ui.modules.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.ui.components.CircularProgressLoading
import com.denisvieira05.githubusersearch.ui.modules.homescreen.components.SuggestedUserList
import com.denisvieira05.githubusersearch.ui.modules.homescreen.components.UserSearchSection
import com.denisvieira05.githubusersearch.ui.utils.fontDimensionResource

@Composable
fun HomeScreen(
    navigateToUserDetail: (userName: String) -> Unit,
    navigateToSuggestedUsers: () -> Unit,
    navigateToFavoritedUser: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val (searchText, setSearchText) = rememberSaveable { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

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
            onSearchFieldChange = {  setSearchText(it) }
        )

        TextButton(onClick = { navigateToFavoritedUser() }) {
            Text(
                fontSize = (fontDimensionResource(id = R.dimen.normal_font_size)),
                text = stringResource(R.string.meus_favoritos)
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.minimum_space_size)))
            Icon(
                Icons.Filled.Favorite,
                null,
                Modifier.size(dimensionResource(id = R.dimen.favorite_icon_size))
            )

        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.normal_space_size)))

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
