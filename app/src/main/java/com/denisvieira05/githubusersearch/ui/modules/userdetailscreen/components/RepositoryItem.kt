package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.domain.model.Repository
import com.denisvieira05.githubusersearch.ui.components.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryItem(
    repository: Repository,
    onPress: () -> Unit
) {
    val (_, name, description) = repository

    ElevatedCard(
        onClick = { onPress() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.min_space_size),
                    vertical = dimensionResource(id = R.dimen.min_space_size)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier.height(
                    dimensionResource(id = R.dimen.min_space_size)
                )
            )
            Text(
                name,
                fontWeight = FontWeight.SemiBold,
            )
            description?.let {
                Text(
                    it,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}