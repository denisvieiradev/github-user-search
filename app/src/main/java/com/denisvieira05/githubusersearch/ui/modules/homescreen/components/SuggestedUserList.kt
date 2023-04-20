package com.denisvieira05.githubusersearch.ui.modules.homescreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.ui.utils.fontDimensionResource

@Composable
fun SuggestedUserList(
    suggestedUsers: List<SuggestedUser>,
    onPressUserItem: (userName: String) -> Unit
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.suggested_user_header_text),
                fontWeight = FontWeight.Bold,
                fontSize = fontDimensionResource(id = R.dimen.normal_font_size),
                textAlign = TextAlign.Center
            )
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(R.string.suggested_user_header_see_all_action),
                    fontSize = fontDimensionResource(id = R.dimen.normal_font_size),
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(count = suggestedUsers.size) {
                val (_, userName, avatarUrl) = suggestedUsers[it]
                UserSuggestedItem(
                    userName = userName,
                    avatarUrl = avatarUrl,
                    onPress = { onPressUserItem(userName) }
                )
            }
        }
    }

}
