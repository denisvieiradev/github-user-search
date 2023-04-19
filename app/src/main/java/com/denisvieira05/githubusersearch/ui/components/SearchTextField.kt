package com.denisvieira05.githubusersearch.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.TextStyle.Companion
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    onValueChange: (String) -> Unit = {},
) {
    var searchText by remember {
        mutableStateOf("")
    }

    ElevatedCard {
        TextField(
            value = searchText,
            textStyle = TextStyle.Default.copy(fontSize = 16.sp),
            onValueChange = {
                searchText = it
                onValueChange(it)
            },
            placeholder = { Text("Enter Github Username", fontSize = 16.sp) },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            leadingIcon = {
                IconButton(onClick = { /* Ação do ícone de pesquisa */ }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }

}
