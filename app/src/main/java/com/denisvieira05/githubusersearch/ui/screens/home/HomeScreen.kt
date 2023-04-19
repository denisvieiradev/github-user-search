package com.denisvieira05.githubusersearch.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.ui.components.AppToolbar
import com.denisvieira05.githubusersearch.ui.components.SearchTextField
import com.denisvieira05.githubusersearch.ui.theme.Grey
import com.denisvieira05.githubusersearch.ui.theme.Purple40
import com.denisvieira05.githubusersearch.ui.theme.PurpleGrey40
import com.denisvieira05.githubusersearch.ui.theme.VeryLightGrey

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by remember { viewModel.uiState }
    val coroutineScope = rememberCoroutineScope()
    val lazyColumnState = rememberLazyListState()
    val suggestedUsers by remember {
        derivedStateOf { uiState.suggestedUsers }
    }
    val isLoading by remember {
        derivedStateOf { uiState.isLoading }
    }

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
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Welcome to Github User Search",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(24.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier.padding(start = 32.dp, end = 32.dp)
            ) {
                SearchTextField()
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = {  }
            ) {
                Text(
                    text = "BUSCAR",
                    fontSize = 16.sp
                )
            }
        }
    }
//
//    Scaffold(
//        topBar = {
////            AnimatedVisibility(
////                visible = isNotScrolling,
////                enter = expandVertically(
////                    tween(delayMillis = 200, durationMillis = 500)
////                ),
////                exit = shrinkVertically()
////            ) {
//            AppToolbar(
//                title = stringResource(id = R.string.app_name)
//            )
////            }
//        },
//        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight(),
//        content = {
//            Surface {
//                Column(
//                    modifier = modifier.fillMaxSize(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    CircularProgressIndicator(
//                        modifier = Modifier
//                            .padding(horizontal = 6.dp)
//                            .size(36.dp),
//                        color = Purple40
//                    )
////                Box(
////                    modifier = Modifier
////                        .padding(top = it.calculateTopPadding())
////                        .fillMaxSize()
////                ) {
////
////                }
//                    Row(
//                        modifier = modifier.fillMaxSize()
//                    ) {
//
//                        Text("Deslize o card para mais")
//                        Icon(Icons.Default.Add, contentDescription = "Deslize o card para mais")
////                    TextFieldValue("Teste")
////                    SearchTextField(
////                        TextFieldValue("Teste"),
////                        {  },
////                        {   },
////                        {  },
////                        false,
////                        false,
////                        modifier.weight(1f)
////                    )
//                    }
//
////                Spacer(modifier = Modifier.height(20.dp))
//                }
//
//
////            Surface(
////                modifier = modifier
////                    .then(
////                        Modifier
////                            .height(56.dp)
////                            .padding(
////                                top = 8.dp,
////                                bottom = 8.dp,
////                                start = 16.dp,
////                                end = 16.dp
////                            )
////                    ),
////                color = Color(0xffF5F5F5),
////                shape = RoundedCornerShape(percent = 50),
////            ) {
////
////            }
//            }
//        })
}