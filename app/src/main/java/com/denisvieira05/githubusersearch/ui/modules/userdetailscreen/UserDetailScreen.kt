package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.ui.components.AppTopBar
import com.denisvieira05.githubusersearch.ui.components.SearchTextField
import com.denisvieira05.githubusersearch.ui.modules.homescreen.components.SuggestedUserList
import com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.components.UserDetailHeader
import com.denisvieira05.githubusersearch.ui.theme.VeryLightGrey

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    userName: String?,
    viewModel: UserDetailViewModel = hiltViewModel(),
) {
    val uiState by remember { viewModel.uiState }
    val coroutineScope = rememberCoroutineScope()
    val lazyColumnState = rememberLazyListState()
    val user by remember {
        derivedStateOf { uiState.user }
    }
    val isLoading by remember {
        derivedStateOf { uiState.isLoading }
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit, block = {
        viewModel.teste()
    })

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(VeryLightGrey),
        topBar = {
            AppTopBar(
                onClickBack = {
                    navController.popBackStack()
                },
                onClickShare = {
                    shareUser(context)
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .padding(top = it.calculateTopPadding())
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserDetailHeader()
        }
    }
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