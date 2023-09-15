package dev.rranndt.article.presentation.bookmark.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.rranndt.article.R

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Wed, September 13
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: DismissState) {
    val direction = dismissState.dismissDirection
    val color = when (dismissState.dismissDirection) {
        DismissDirection.EndToStart -> MaterialTheme.colorScheme.error.copy(alpha = .5f)
        else -> Color.Transparent
    }
    val alignment = when (direction) {
        DismissDirection.EndToStart -> Alignment.CenterEnd
        else -> Alignment.CenterStart
    }
    val icon = when (direction) {
        DismissDirection.EndToStart -> Icons.Rounded.Delete
        else -> Icons.Rounded.Done
    }
    val scale by animateFloatAsState(
        targetValue = if (dismissState.targetValue == DismissValue.Default) .7f else 1f,
        label = ""
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        contentAlignment = alignment
    ) {
        Icon(
            modifier = Modifier.scale(scale),
            imageVector = icon,
            contentDescription = stringResource(id = R.string.desc_icon_dismiss_background),
            tint = MaterialTheme.colorScheme.background
        )
    }
}