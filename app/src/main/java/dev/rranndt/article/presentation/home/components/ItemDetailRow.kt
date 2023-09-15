package dev.rranndt.article.presentation.home.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.rranndt.article.presentation.theme.ArticleTheme

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
@Composable
fun ItemDetailRow(
    modifier: Modifier = Modifier,
    value1: String,
    color1: Color = Color.Unspecified,
    value2: String,
    color2: Color = Color.Unspecified,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = value1,
            style = MaterialTheme.typography.bodySmall,
            color = color1
        )
        Text(
            text = value2,
            style = MaterialTheme.typography.bodySmall,
            color = color2
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ItemDetailRowPreview() {
    ArticleTheme {
        ItemDetailRow(
            value1 = "Item 1",
            value2 = "Item 2"
        )
    }
}