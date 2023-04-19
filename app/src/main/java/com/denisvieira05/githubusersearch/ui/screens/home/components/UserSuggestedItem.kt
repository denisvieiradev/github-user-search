package com.denisvieira05.githubusersearch.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.denisvieira05.githubusersearch.R

@Composable
fun UserSuggestedItem(
    userName: String
) {
    Box(
        modifier = Modifier
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .border(1.dp, Color.LightGray)
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val image = painterResource(R.drawable.octa2)
            Image(
                painter = image,
                contentDescription = "Octacat",
                modifier = Modifier
                    .size(110.dp)
            )
            Text(userName)
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = { }
            ) {
                Text(
                    text = "VER",
                    fontSize = 16.sp
                )
            }
        }
    }

}