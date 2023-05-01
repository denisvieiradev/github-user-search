package com.denisvieira05.githubusersearch.ui.modules.suggestedusersscreen.components

import androidx.compose.foundation.lazy.grid.LazyGridScope
import com.denisvieira05.githubusersearch.domain.model.SuggestedUser
import com.denisvieira05.githubusersearch.ui.modules.homescreen.components.UserSuggestedItem


fun LazyGridScope.suggestedUsersLazyGrid(
    suggestedUsers: List<SuggestedUser>,
    onPressItem: (userName: String) -> Unit
) {
    items(count = suggestedUsers.size) {
        val (_, userName, avatarUrl) = suggestedUsers[it]
        UserSuggestedItem(
            userName = userName,
            avatarUrl = avatarUrl,
            onPress = { onPressItem(userName) }
        )
    }
}