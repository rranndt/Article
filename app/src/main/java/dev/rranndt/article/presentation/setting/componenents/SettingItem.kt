package dev.rranndt.article.presentation.setting.componenents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.rranndt.article.R

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Thu, September 14
 */
@Composable
fun SettingItem(
    modifier: Modifier = Modifier,
    iconLabel: Painter,
    label: String,
    hasArrow: Boolean = false,
    content: (@Composable RowScope.() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(45.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(start = 16.dp),
            painter = iconLabel,
            contentDescription = label
        )
        Text(
            modifier = Modifier
                .padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                )
                .weight(1f),
            text = label,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal
        )
        if (content != null) {
            content()
        }
        if (hasArrow) {
            Icon(
                modifier = Modifier
                    .padding(
                        vertical = 8.dp,
                        horizontal = 16.dp
                    ),
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = label
            )
        }
    }
}