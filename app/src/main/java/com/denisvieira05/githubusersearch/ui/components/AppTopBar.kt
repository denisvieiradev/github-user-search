package com.denisvieira05.githubusersearch.ui.components

import androidx.compose.foundation.layout.RowScope
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
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
    onClickBack: () -> Unit) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            Text(title)
        },
        navigationIcon = {
            IconButton(onClick = { onClickBack() }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        }, actions = actions
    )
}