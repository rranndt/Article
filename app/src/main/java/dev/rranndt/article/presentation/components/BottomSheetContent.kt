package dev.rranndt.article.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.rranndt.article.R
import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.domain.model.Source
import dev.rranndt.article.presentation.home.components.ItemDetailRow
import dev.rranndt.article.presentation.theme.ArticleTheme

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
@Composable
fun BottomSheetContent(
    article: Article,
    onReadFullNewsButtonClick: () -> Unit
) {
    Surface(modifier = Modifier.padding(16.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = article.title ?: "",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = article.description ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            ItemDetailRow(
                value1 = article.author ?: "",
                value2 = article.source.name ?: ""
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomButton(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(id = R.string.bottom_sheet_button_title),
                onClick = onReadFullNewsButtonClick,
                button = true
            )
        }
    }
}

@Preview
@Composable
fun BottomSheetContentPreview() {
    ArticleTheme {
        BottomSheetContent(
            article = Article(
                source = Source(id = "Vox", name = "Vox"),
                author = "Asep Bengkel",
                title = "Apple's China Dependency Spooks Investors After Ban - The Wall Street Journal",
                description = "Covid shots are — and are not — like your annual flu vaccine.",
                url = "Youtube.com",
                urlToImage = null,
                publishedAt = "September 2023",
                content = "New Mexico Gov. Michelle Lujan Grisham",
                isBookmark = false
            ),
            onReadFullNewsButtonClick = {}
        )
    }
}