package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.ui.components.AppTopBar
import com.denisvieira05.githubusersearch.ui.components.CircularProgressLoading
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.components.UserDetailHeader
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.components.UserRepositoriesList
import com.denisvieira05.githubusersearch.ui.theme.VeryLightGrey

@Composable
fun UserDetailScreen(
    navController: NavController,
    userName: String?,
    viewModel: UserDetailViewModel = hiltViewModel(),
) {
    val uiState by remember { viewModel.uiState }
    val lazyColumnState = rememberLazyListState()
    val user by remember {
        derivedStateOf { uiState.user }
    }
    val repositories by remember {
        derivedStateOf { uiState.repositories }
    }
    val isLoading by remember {
        derivedStateOf { uiState.isLoading }
    }

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchData()
    })

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(id = R.dimen.double_space_size),
                end = dimensionResource(id = R.dimen.double_space_size)
            ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.min_space_size)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            AppTopBar(
                title = "@$userName",
                onClickBack = {
                    navController.popBackStack()
                },
                onClickShare = {
//                    shareUser(context)
                }
            )
        }

        item {
            if (isLoading) {
                CircularProgressLoading(
                    size = dimensionResource(id = R.dimen.circular_progress_loading_box)
                )
            }
        }

        item {
            if (user != null) {
                UserDetailHeader(user!!)
            }
        }

        UserRepositoriesList(
            repositories = repositories
        ) {
            openUserRepositoryOnBrowser()
        }

    }

//    Scaffold(
//        modifier = Modifier
//            .verticalScroll(scrollState)
//            .background(VeryLightGrey),
//        topBar = {
//            AppTopBar(
//                title = "@$userName",
//                onClickBack = {
//                    navController.popBackStack()
//                },
//                onClickShare = {
////                    shareUser(context)
//                }
//            )
//        }
//    ) {
//        LazyColumn {
//            item {
////                if (isLoading) {
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(50.dp),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        CircularProgressLoading(size = dimensionResource(id = R.dimen.circular_progress_loading_box))
//                    }
////                }
//            }
//

//

//        }
//    }
}

@Composable
fun Content() {

}

fun openUserRepositoryOnBrowser() {

}

fun shareUser(context: Context) {
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "text")
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(intent, "Compartilhar via")
    startActivity(context, shareIntent, Bundle())
}