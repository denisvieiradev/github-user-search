package com.denisvieira05.githubusersearch.ui.modules.userdetailscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import com.denisvieira05.githubusersearch.ui.components.GlideImage

val fakeUser = UserDetail(
    id = 8844649,
    completeName = "Denis Vieira",
    userName = "denisvieira05",
    avatarUrl = "https://avatars.githubusercontent.com/u/8844649?v=4",
    htmlUrl = "https://avatars.githubusercontent.com/u/8844649?v=4",
    followers = 69,
    following = 163,
    repositories = 60,
    blog = "https://denisvieira.notion.site",
    bio = "Software Engineer from Maceió-AL \r\n/ Brazil",
    twitterUsername = "denisvieira05"
)

@Composable
fun UserDetailHeader(user: UserDetail = fakeUser) {
    val (_, completeName, userName, avatarUrl, htmlUrl, followers,
        following, repositories, blog, bio, twitterUsername
    ) = user
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        avatarUrl?.let {
            GlideImage(
                imageUrl = avatarUrl,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.user_profile_image_size))
                    .clip(CircleShape),
                contentDescription = stringResource(R.string.user_profile_image_content_description),
                loadingSize = dimensionResource(id = R.dimen.user_profile_image_size)
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.normal_space_size)))
        Text(
            completeName,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        bio?.let {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.min_space_size)))
            Text(
                it,
                textAlign = TextAlign.Center,
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.medium_space_size)))
        UserStats(
            followers,
            following,
            repositories
        )

    }
}