package dev.rranndt.article.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.rranndt.article.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Thu, September 14
 */
@Composable
fun BoxScope.ScrollToTopButton(
    coroutineScope: CoroutineScope,
    listState: LazyListState,
    showButton: Boolean
) {
    AnimatedVisibility(
        visible = showButton,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = Modifier
            .padding(16.dp)
            .align(Alignment.BottomCenter)
    ) {
        FilledIconButton(
            onClick = {
                coroutineScope.launch {
                    listState.animateScrollToItem(0)
                }
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_up),
                contentDescription = stringResource(id = R.string.desc_scroll_to_top_button),
            )
        }
    }
}