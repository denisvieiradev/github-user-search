package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.denisvieira05.githubusersearch.R

@Composable
fun UserStats(
    followers: Long,
    following: Long,
    repositories: Long,
) {
    ElevatedCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.medium_space_size)),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.user_detail_followers),
                )
                Text(
                    followers.toString()
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.user_detail_following)
                )
                Text(
                    following.toString()
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.user_detail_repositories_title)
                )
                Text(
                    repositories.toString()
                )
            }
        }
    }
}