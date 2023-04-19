package com.denisvieira05.githubusersearch.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun CircularProgressLoading(size: Dp = 40.dp) {
    CircularProgressIndicator(
        modifier = Modifier
            .size(size)
            .padding(8.dp)
    )
}
