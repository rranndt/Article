package dev.rranndt.article.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.rranndt.article.R
import dev.rranndt.article.presentation.theme.ArticleTheme

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
@Composable
fun RetryContent(
    modifier: Modifier = Modifier,
    error: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_no_connection),
            contentDescription = stringResource(id = R.string.desc_image_retry_content)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.retry_content_title),
            style = MaterialTheme.typography.headlineSmall,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = error)
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(
            title = stringResource(id = R.string.retry_button_title),
            onClick = onRetry,
            textButton = true
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun RetryContentPreview() {
    ArticleTheme {
        RetryContent(
            error = "No internet connection!",
            onRetry = {}
        )
    }
}