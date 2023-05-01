package com.denisvieira05.githubusersearch.ui.modules.favoritedusers.components

import androidx.compose.foundation.lazy.grid.LazyGridScope
import com.denisvieira05.githubusersearch.domain.model.UserDetail
import com.denisvieira05.githubusersearch.ui.modules.homescreen.components.UserSuggestedItem


fun LazyGridScope.favoritedUsersLazyGrid(
    favoritedUsers: List<UserDetail>,
    onPressItem: (userName: String) -> Unit
) {
    items(count = favoritedUsers.size) {
        val userName = favoritedUsers[it].userName
        UserSuggestedItem(
            userName = userName,
            avatarUrl = favoritedUsers[it].avatarUrl,
            onPress = { onPressItem(userName) }
        )
    }
}