package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.domain.model.Repository
import com.denisvieira05.githubusersearch.ui.components.EmptyState
import com.denisvieira05.githubusersearch.ui.utils.fontDimensionResource

fun LazyListScope.UserRepositoriesList(
    repositories: List<Repository>?,
    onPressItem: (repositoryUrl: String) -> Unit
) {
    item {
        Spacer(
            modifier = Modifier.height(
                dimensionResource(id = R.dimen.medium_space_size)
            )
        )
        Text(
            text = stringResource(R.string.user_detail_repositories_title),
            fontWeight = FontWeight.Bold,
            fontSize = fontDimensionResource(id = R.dimen.normal_font_size),
        )
        Spacer(
            modifier = Modifier.height(
                dimensionResource(id = R.dimen.normal_space_size)
            )
        )
    }

    if (repositories != null && repositories.isNotEmpty()) {
        items(
            count = repositories.size
        ) {
            RepositoryItem(
                repository = repositories[it],
                onPress = { onPressItem(repositories[it].htmlUrl) }
            )
        }
    }

    item {
        if (repositories != null && repositories.isEmpty()) {
            EmptyState(emptyText = stringResource(R.string.repositories_empty_list))
        }
    }
}