package dev.rranndt.article.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.rranndt.article.R

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Tue, September 12
 */
@Composable
fun CustomEmptyContent(
    image: Painter,
    title: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = image,
            contentDescription = stringResource(id = R.string.desc_image_custom_empty_content)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = title)
    }
}