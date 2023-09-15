package dev.rranndt.article.presentation.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.presentation.components.ImageHolder
import dev.rranndt.article.presentation.detail.components.DetailTopBar
import dev.rranndt.article.util.UiComponent
import dev.rranndt.article.util.parseStringToLocalDateTime

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sun, September 10
 */
@Composable
fun DetailScreen(
    article: Article,
    onEvent: (DetailEvent) -> Unit,
    onBackPress: () -> Unit,
    uiComponent: UiComponent?,
) {
    val context = LocalContext.current

    // Snackbar state
    val snackbarHostState = remember { SnackbarHostState() }

    // Status bookmark
//    var isBookmarked = article.isBookmark
    var isBookmarked by rememberSaveable { mutableStateOf(article.isBookmark) }

    LaunchedEffect(key1 = uiComponent) {
        uiComponent?.let {
            when (uiComponent) {
                is UiComponent.SnackBar -> snackbarHostState.showSnackbar(uiComponent.message)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            DetailTopBar(
                onBackPress = onBackPress,
                onBookmarkClick = {
                    isBookmarked = !isBookmarked
                    onEvent(DetailEvent.InsertDeleteArticle(article = article))
                    onEvent(DetailEvent.UpdateBookmarkArticle(article = article, isBookmarked))
                },
                onShareClick = {
                    Intent(Intent.ACTION_SEND).also {
                        it.putExtra(Intent.EXTRA_TEXT, article.url)
                        it.type = "text/plain"
                        if (it.resolveActivity(context.packageManager) != null) {
                            context.startActivity(it)
                        }
                    }
                },
                onBrowsingClick = {
                    Intent(Intent.ACTION_VIEW).also {
                        it.data = Uri.parse(article.url)
                        if (it.resolveActivity(context.packageManager) != null) {
                            context.startActivity(it)
                        }
                    }
                },
                isBookmarked = isBookmarked,
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = article.title ?: "",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = parseStringToLocalDateTime(article.publishedAt ?: ""),
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = article.author ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = article.source.name ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = article.description ?: "",
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 25.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            ImageHolder(imageUrl = article.urlToImage)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = article.content ?: "",
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 25.sp
            )
        }
    }
}