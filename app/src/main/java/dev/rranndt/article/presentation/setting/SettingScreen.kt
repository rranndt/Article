package dev.rranndt.article.presentation.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.rranndt.article.BuildConfig
import dev.rranndt.article.R
import dev.rranndt.article.presentation.setting.componenents.CustomDialog
import dev.rranndt.article.presentation.setting.componenents.SettingItem

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Thu, September 14
 */
@Composable
fun SettingScreen() {
    var showAppInfo by remember { mutableStateOf(false) }
    var showAbout by remember { mutableStateOf(false) }

    if (showAppInfo) {
        CustomDialog(
            onDismissRequest = { showAppInfo = false },
            title = stringResource(id = R.string.app_name),
            description = stringResource(id = R.string.setting_app_version, BuildConfig.VERSION_NAME),
            painter = painterResource(id = R.drawable.ic_logo),
            imageDescription = stringResource(id = R.string.desc_logo_app)
        )
    }
    if (showAbout) {
        CustomDialog(
            onDismissRequest = { showAbout = false },
            title = stringResource(id = R.string.setting_profile_picture),
            description = stringResource(id = R.string.setting_profile_email),
            painter = painterResource(id = R.drawable.profile),
            imageDescription = stringResource(id = R.string.desc_about)
        )
    }

    Scaffold(
        topBar = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = stringResource(id = R.string.setting_title),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
        ) {
            SettingItem(
                modifier = Modifier.clickable { showAppInfo = true },
                iconLabel = painterResource(id = R.drawable.ic_info),
                label = stringResource(id = R.string.setting_app_info)
            )
            Divider()
            SettingItem(
                modifier = Modifier.clickable { showAbout = true },
                iconLabel = painterResource(id = R.drawable.ic_about),
                label = stringResource(id = R.string.setting_about),
            )
        }
    }
}