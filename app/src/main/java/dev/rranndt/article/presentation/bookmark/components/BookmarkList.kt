package dev.rranndt.article.presentation.bookmark.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.rranndt.article.R
import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.presentation.bookmark.BookmarkState
import dev.rranndt.article.presentation.components.CustomEmptyContent
import dev.rranndt.article.presentation.components.ScrollToTopButton
import dev.rranndt.article.presentation.home.components.NewsItem
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Mon, September 11
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkList(
    state: BookmarkState,
    onItemClick: (Article) -> Unit,
    onDeleteArticle: (Article) -> Unit,
    snackbarResult: (Article) -> Unit,
    coroutineScope: CoroutineScope,
    listState: LazyListState,
    showButton: Boolean
) {
    Box {
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 80.dp, end = 16.dp, start = 16.dp),
        ) {
            items(
                state.articles,
                key = { it.url.hashCode() }
            ) { article ->
                var show by remember { mutableStateOf(true) }
                val dismissState = rememberDismissState(
                    confirmValueChange = {
                        if (it == DismissValue.DismissedToEnd) show = !show
                        it != DismissValue.DismissedToEnd
                    }, positionalThreshold = { 200.dp.toPx() }
                )

                if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                    LaunchedEffect(key1 = Unit) {
                        onDeleteArticle(article)
                        snackbarResult(article)
                    }
                }
                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    background = { DismissBackground(dismissState = dismissState) },
                    dismissContent = {
                        NewsItem(
                            article = article,
                            onItemClick = onItemClick,
                        )
                    }
                )
            }
        }
        ScrollToTopButton(
            coroutineScope = coroutineScope,
            listState = listState,
            showButton = showButton
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.articles.isEmpty()) {
            CustomEmptyContent(
                image = painterResource(id = R.drawable.ic_bookmarks_empty),
                title = stringResource(id = R.string.title_empty_content)
            )
        }
    }
}
