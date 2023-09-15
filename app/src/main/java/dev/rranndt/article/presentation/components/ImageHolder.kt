package dev.rranndt.article.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.rranndt.article.R
import dev.rranndt.article.presentation.theme.ArticleTheme

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
@Composable
fun ImageHolder(
    modifier: Modifier = Modifier,
    imageUrl: String?,
) {
    AsyncImage(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .aspectRatio(16 / 9f),
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = stringResource(id = R.string.desc_coil_image_content),
        contentScale = ContentScale.Crop,
        placeholder = painterResource(id = R.drawable.placeholder_loading),
        error = painterResource(id = R.drawable.placeholder_default),
    )
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ImageHolderPreview() {
    ArticleTheme {
        ImageHolder(imageUrl = "")
    }
}