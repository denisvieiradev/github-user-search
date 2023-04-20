package com.denisvieira05.githubusersearch.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.denisvieira05.githubusersearch.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    onClickShare: () -> Unit,
    onClickBack: () -> Unit) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            Text(stringResource(R.string.app_name))
        },
        navigationIcon = {
            IconButton(onClick = { onClickBack() }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        }, actions = {
            IconButton(onClick = { onClickShare() }) {
                Icon(Icons.Filled.Share, null)
            }
        }
    )
}