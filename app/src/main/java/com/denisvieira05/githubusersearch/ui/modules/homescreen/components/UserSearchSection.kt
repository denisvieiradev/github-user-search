package com.denisvieira05.githubusersearch.ui.modules.homescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.denisvieira05.githubusersearch.R
import com.denisvieira05.githubusersearch.ui.components.SearchTextField
import com.denisvieira05.githubusersearch.ui.utils.fontDimensionResource


@Composable
fun UserSearchSection(
    onPressSearch: () -> Unit,
    onSearchFieldChange: (String) -> Unit = {},
) {
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.double_space_size)))
    Image(
        painter = painterResource(R.drawable.octa2),
        contentDescription = stringResource(R.string.octacat_github),
        modifier = Modifier.size(dimensionResource(id = R.dimen.header_home_screen_image)),
    )
    Text(
        text = stringResource(id = R.string.welcome_phrase),
        fontWeight = FontWeight.Bold,
        fontSize = fontDimensionResource(id = R.dimen.medium_font_size),
        modifier = Modifier.padding(dimensionResource(id = R.dimen.medium_space_size)),
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_space_size)))
    Box(
        modifier = Modifier.padding(
            start = dimensionResource(id = R.dimen.double_space_size),
            end = dimensionResource(id = R.dimen.double_space_size)
        )
    ) {
        SearchTextField { currentText ->
            onSearchFieldChange(currentText)
        }
    }
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.small_space_size)))
    Button(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.normal_space_size)),
        onClick = {
            onPressSearch()
        }
    ) {
        Text(
            text = stringResource(R.string.search_button),
            fontSize = fontDimensionResource(id = R.dimen.normal_font_size)
        )
    }
}