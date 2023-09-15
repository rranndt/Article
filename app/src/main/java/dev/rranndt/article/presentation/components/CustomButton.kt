package dev.rranndt.article.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.rranndt.article.presentation.theme.ArticleTheme

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
    textButton: Boolean = false,
    button: Boolean = false
) {
    if (textButton) {
        TextButton(
            modifier = modifier,
            onClick = onClick,
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = title)
        }
    }

    if (button) {
        Button(
            modifier = modifier,
            onClick = onClick,
        ) {
            Text(text = title)
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CustomButtonPreview() {
    ArticleTheme {
        CustomButton(
            title = "Article",
            onClick = {},
            textButton = true
        )
    }
}