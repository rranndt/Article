package dev.rranndt.article.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.rranndt.article.R
import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.presentation.components.BottomSheetContent
import dev.rranndt.article.presentation.home.components.CategoryTabRow
import dev.rranndt.article.presentation.home.components.NewsList
import kotlinx.coroutines.launch

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    onReadFullNewsButtonClick: (Article) -> Unit,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // List of category
    val categories = context.resources.getStringArray(R.array.categories)
    val pagerState = rememberPagerState { categories.size }

    // Bottom sheet
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var shouldBottomSheetShow by remember { mutableStateOf(false) }

    // Scroll to top
    val listState = rememberLazyListState()
    val showButton by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onEvent(HomeEvent.OnCategoryChanged(category = categories[page]))
        }
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo_title),
                    contentDescription = stringResource(id = R.string.desc_home_title_logo)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.home_short_desc),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = paddingValues)
        ) {
            CategoryTabRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                pagerState = pagerState,
                categories = categories.toList(),
                onTabSelected = { index ->
                    coroutineScope.launch { pagerState.animateScrollToPage(page = index) }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                state = pagerState,
            ) {
                NewsList(
                    state = state,
                    onItemClick = { article ->
                        shouldBottomSheetShow = true
                        onEvent(HomeEvent.OnHeadlineNewsClick(article = article))
                    },
                    onRetry = { onEvent(HomeEvent.OnCategoryChanged(category = state.category)) },
                    coroutineScope = coroutineScope,
                    listState = listState,
                    showButton = showButton
                )
            }
        }
        if (shouldBottomSheetShow) {
            ModalBottomSheet(
                onDismissRequest = { shouldBottomSheetShow = false },
                sheetState = sheetState
            ) {
                state.selectedArticle?.let { article ->
                    BottomSheetContent(
                        article = article,
                        onReadFullNewsButtonClick = {
                            onReadFullNewsButtonClick(article)
                            coroutineScope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) shouldBottomSheetShow = false
                            }
                        },
                    )
                }
            }
        }
    }
}