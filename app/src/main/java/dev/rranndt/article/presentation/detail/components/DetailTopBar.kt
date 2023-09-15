package dev.rranndt.article.presentation.detail.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.rranndt.article.R
import dev.rranndt.article.presentation.theme.ArticleTheme

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sun, September 10
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    onBackPress: () -> Unit,
    onBookmarkClick: () -> Unit,
    onShareClick: () -> Unit,
    onBrowsingClick: () -> Unit,
    isBookmarked: Boolean
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {},
        navigationIcon = {
            IconButton(onClick = onBackPress) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(id = R.string.desc_detail_top_bar_navigation_icon)
                )
            }
        },
        actions = {
            IconButton(onClick = onBookmarkClick) {
                Icon(
                    painter = painterResource(id = if (isBookmarked) R.drawable.ic_bookmark_filled else R.drawable.ic_bookmark),
                    contentDescription = stringResource(id = R.string.desc_action_bookmark)
                )
            }
            IconButton(onClick = onShareClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = stringResource(id = R.string.desc_action_share)
                )
            }
            IconButton(onClick = onBrowsingClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_browser),
                    contentDescription = stringResource(id = R.string.desc_action_browsing)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailTopBarPreview() {
    ArticleTheme {
        DetailTopBar(
            onBackPress = {},
            onShareClick = {},
            onBrowsingClick = {},
            onBookmarkClick = {},
            isBookmarked = true
        )
    }
}