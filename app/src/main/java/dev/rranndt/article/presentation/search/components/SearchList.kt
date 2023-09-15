package dev.rranndt.article.presentation.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.rranndt.article.R
import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.presentation.components.CustomEmptyContent
import dev.rranndt.article.presentation.components.RetryContent
import dev.rranndt.article.presentation.components.ScrollToTopButton
import dev.rranndt.article.presentation.components.ShimmerEffect
import dev.rranndt.article.presentation.home.components.NewsItem
import dev.rranndt.article.presentation.search.SearchState
import kotlinx.coroutines.CoroutineScope

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sun, September 10
 */
@Composable
fun SearchList(
    state: SearchState,
    onItemClick: (Article) -> Unit,
    onRetry: () -> Unit,
    coroutineScope: CoroutineScope,
    listState: LazyListState,
    showButton: Boolean
) {
    Box {
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 80.dp, end = 16.dp, start = 16.dp)
        ) {
            items(
                state.articles,
                key = { it.url.hashCode() }
            ) { article ->
                NewsItem(
                    article = article,
                    onItemClick = onItemClick
                )
            }
            item {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (state.isLoading) {
                        ShimmerEffect()
                    }
                }
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
        if (state.searchQuery.isEmpty() && state.articles.isEmpty()) {
            CustomEmptyContent(
                image = painterResource(id = R.drawable.ic_find),
                title = stringResource(id = R.string.search_empty_content)
            )
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (state.error != null) {
                if (!state.isLoading) {
                    RetryContent(
                        error = state.error,
                        onRetry = onRetry,
                    )
                }
            }
        }
    }
}