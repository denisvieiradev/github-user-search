package com.denisvieira05.githubusersearch.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.ui.theme.Grey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSuggestedItem(
    userName: String
) {
    Box(
        modifier = Modifier
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        ElevatedCard(
            onClick = { /*TODO*/ },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val image = painterResource(R.drawable.octa2)
                Image(
                    painter = image,
                    contentDescription = "Octacat",
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .border(1.dp, Grey)
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    "@$userName",
                    fontWeight = FontWeight.SemiBold,
                )
//            Button(
//                modifier = Modifier.padding(vertical = 24.dp),
//                onClick = { }
//            ) {
//                Text(
//                    text = "VISIT",
//                    fontSize = 16.sp
//                )
//            }
            }
        }

    }

}