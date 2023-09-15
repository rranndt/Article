package dev.rranndt.article.presentation.bookmark

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.rranndt.article.R
import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.presentation.bookmark.components.AlertDialogContent
import dev.rranndt.article.presentation.bookmark.components.BookmarkList
import dev.rranndt.article.presentation.components.BottomSheetContent
import dev.rranndt.article.util.UiText
import kotlinx.coroutines.launch

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sun, September 10
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    state: BookmarkState,
    onEvent: (BookmarkEvent) -> Unit,
    onReadFullNewsButtonClick: (Article) -> Unit
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Bottom sheet
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var shouldBottomSheetShow by remember { mutableStateOf(false) }

    // Option menu
    var displayMenu by remember { mutableStateOf(false) }

    // Alert dialog
    var showAlertDialog by remember { mutableStateOf(false) }

    // Snackbar state
    val snackbarHostState = remember { SnackbarHostState() }

    // Scroll to top
    val listState = rememberLazyListState()
    val showButton by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }

    if (showAlertDialog) {
        AlertDialogContent(
            onDismissRequest = { showAlertDialog = false },
            onConfirmation = {
                showAlertDialog = false
                if (state.articles.isNotEmpty()) {
                    onEvent(BookmarkEvent.ClearArticle)
                } else {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            UiText.Resource(R.string.snack_bar).asString(context)
                        )
                    }
                }
            },
            dialogTitle = stringResource(id = R.string.dialog_title),
            dialogText = stringResource(id = R.string.dialog_text),
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.bookmark_title),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Box {
                    IconButton(onClick = { displayMenu = !displayMenu }) {
                        Icon(
                            imageVector = Icons.Rounded.MoreVert,
                            contentDescription = stringResource(id = R.string.desc_icon_more)
                        )
                    }
                    DropdownMenu(
                        expanded = displayMenu,
                        onDismissRequest = { displayMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(text = stringResource(id = R.string.clear_bookmark))
                            },
                            onClick = { showAlertDialog = true }
                        )
                    }
                }
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues = paddingValues)
        ) {
            BookmarkList(
                state = state,
                onItemClick = { article ->
                    shouldBottomSheetShow = true
                    onEvent(BookmarkEvent.OnHeadlineNewsClick(article = article))
                },
                onDeleteArticle = { onEvent(BookmarkEvent.DeleteArticle(it)) },
                snackbarResult = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            UiText.Resource(R.string.snackbar_item_removed).asString(context)
                        )
                    }
                },
                coroutineScope = coroutineScope,
                listState = listState,
                showButton = showButton
            )
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
}