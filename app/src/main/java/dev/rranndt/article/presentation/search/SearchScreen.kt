package dev.rranndt.article.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.presentation.components.BottomSheetContent
import dev.rranndt.article.presentation.search.components.SearchBar
import dev.rranndt.article.presentation.search.components.SearchList
import kotlinx.coroutines.launch

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sun, September 10
 */
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: SearchState,
    onEvent: (SearchEvent) -> Unit,
    onReadFullNewsButtonClick: (Article) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    // Keyboard options
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    // Bottom sheet
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var shouldBottomSheetShow by remember { mutableStateOf(false) }

    // Scroll to top
    val listState = rememberLazyListState()
    val showButton by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                SearchBar(
                    modifier = Modifier.focusRequester(focusRequester),
                    value = state.searchQuery,
                    onInputValueChange = { query ->
                        onEvent(SearchEvent.OnSearchQueryChanged(searchQuery = query))
                    },
                    onCloseIconClick = { onEvent(SearchEvent.OnCloseIconClick) },
                    onSearchIconClick = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    },
                )
            }
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = paddingValues)
        ) {
            SearchList(
                state = state,
                onItemClick = { article ->
                    shouldBottomSheetShow = true
                    onEvent(SearchEvent.OnHeadlineNewsClick(article = article))
                },
                onRetry = { onEvent(SearchEvent.OnSearchQueryFilled(query = state.searchQuery)) },
                coroutineScope = coroutineScope,
                listState = listState,
                showButton = showButton
            )
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