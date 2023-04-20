package com.denisvieira05.githubusersearch.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressLoading(
    size: Dp = 40.dp,
    contentAlignment: Alignment = Alignment.Center
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = contentAlignment
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(size)
                .padding(8.dp)
        )
    }
}
