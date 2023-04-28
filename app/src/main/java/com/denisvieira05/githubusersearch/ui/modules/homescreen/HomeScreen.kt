package com.denisvieira05.githubusersearch.ui.modules.homescreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.ui.components.CircularProgressLoading
import com.denisvieira05.githubusersearch.ui.components.SearchTextField
import com.denisvieira05.githubusersearch.ui.modules.homescreen.components.SuggestedUserList
import com.denisvieira05.githubusersearch.ui.navigation.ScreenRoutesBuilder.SUGGESTED_USERS_SCREEN_ROUTE
import com.denisvieira05.githubusersearch.ui.navigation.ScreenRoutesBuilder.USER_DETAIL_SCREEN_ROUTE
import com.denisvieira05.githubusersearch.ui.navigation.ScreenRoutesBuilder.buildRouteWithSimpleArgument
import com.denisvieira05.githubusersearch.ui.theme.VeryLightGrey
import com.denisvieira05.githubusersearch.ui.utils.fontDimensionResource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by remember { viewModel.uiState }
    val suggestedUsers by remember {
        derivedStateOf { uiState.suggestedUsers }
    }
    val isLoading by remember {
        derivedStateOf { uiState.isLoading }
    }
    var searchText by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchSuggestedUsers()
    })

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(VeryLightGrey)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .padding(top = it.calculateTopPadding())
                .padding(dimensionResource(id = R.dimen.normal_space_size)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.double_space_size)))
            Image(
                painter = painterResource(R.drawable.octa2),
                contentDescription = stringResource(R.string.octacat_github),
                modifier = Modifier.size(dimensionResource(id = R.dimen.header_home_screen_image)),
            )
            Text(
                text = stringResource(id = R.string.welcome_phrase),
                fontWeight = FontWeight.Bold,
                fontSize = fontDimensionResource(id = R.dimen.medium_font_size),
                modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_space_size)),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.min_space_size)))
            Box(
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.double_space_size),
                    end = dimensionResource(id = R.dimen.double_space_size)
                )
            ) {
                SearchTextField { currentText ->
                    searchText = currentText
                }
            }
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.min_space_size)))
            Button(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.normal_space_size)),
                onClick = {
                    navController.navigate(
                        buildRouteWithSimpleArgument(
                            USER_DETAIL_SCREEN_ROUTE,
                            searchText
                        )
                    )
                }
            ) {
                Text(
                    text = stringResource(R.string.search_button),
                    fontSize = fontDimensionResource(id = R.dimen.normal_font_size)
                )
            }

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressLoading(size = dimensionResource(id = R.dimen.circular_progress_loading_box))
                }
            }

            if (suggestedUsers != null) {
                SuggestedUserList(
                    suggestedUsers!!,
                    onPressUserItem = { userName ->
                        navController.navigate(
                            buildRouteWithSimpleArgument(USER_DETAIL_SCREEN_ROUTE, userName)
                        )
                    },
                    onPressSeeAll = {
                        navController.navigate(
                            SUGGESTED_USERS_SCREEN_ROUTE
                        )
                    }
                )
            }
        }
    }
}