package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.domain.model.Repository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryItem(
    repository: Repository,
    onPress: () -> Unit
) {
    val (_, name, description, htmlUrl, forks, language, stars) = repository

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { onPress() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.small_space_size),
                    vertical = dimensionResource(id = R.dimen.small_space_size)
                )
        ) {
            Spacer(
                modifier = Modifier.height(
                    dimensionResource(id = R.dimen.small_space_size)
                )
            )
            Text(
                name,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_space_size)))
            description?.let {
                Text(
                    it,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_space_size)))

            Row {
                Icon(Icons.Outlined.Star, contentDescription = "Stars")
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_space_size)))
                Text(stars.toString())
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.normal_space_size)))
                Icon(Icons.Outlined.Share, contentDescription = "Forks")
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.small_space_size)))
                Text(forks.toString())
                Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.normal_space_size)))
                language?.let { Text(it) }
            }
        }
    }

}