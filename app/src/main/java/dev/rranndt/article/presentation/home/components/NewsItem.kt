package dev.rranndt.article.presentation.home.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.rranndt.article.R
import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.domain.model.Source
import dev.rranndt.article.presentation.components.ImageHolder
import dev.rranndt.article.presentation.theme.ArticleTheme
import dev.rranndt.article.util.parseStringToLocalDateTime

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    article: Article,
    onItemClick: (Article) -> Unit,
) {
    if (article.title != null && article.source.name != null) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(article) },
            shape = MaterialTheme.shapes.small
        ) {
            Box {
                ImageHolder(imageUrl = article.urlToImage)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .background(
                            Brush.verticalGradient(
                                0F to Color.Transparent,
                                .3F to MaterialTheme.colorScheme.background.copy(alpha = .5f),
                                1F to MaterialTheme.colorScheme.background
                            )
                        )
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp, top = 24.dp),
                        text = article.title,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start,
                        color = colorResource(id = R.color.text_color_reverse)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    ItemDetailRow(
                        modifier = modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                        value1 = article.source.name,
                        color1 = colorResource(id = R.color.text_color_reverse),
                        value2 = parseStringToLocalDateTime(article.publishedAt ?: ""),
                        color2 = colorResource(id = R.color.text_color_reverse)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewsItemPreview() {
    ArticleTheme {
        NewsItem(
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
            onItemClick = {}
        )
    }
}