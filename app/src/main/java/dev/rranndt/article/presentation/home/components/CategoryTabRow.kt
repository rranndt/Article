package dev.rranndt.article.presentation.home.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import dev.rranndt.article.presentation.theme.ArticleTheme

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryTabRow(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    categories: List<String>,
    onTabSelected: (Int) -> Unit,
) {
    ScrollableTabRow(
        modifier = modifier.clip(RoundedCornerShape(8.dp)),
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 0.dp,
        containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
        contentColor = MaterialTheme.colorScheme.onBackground,
        indicator = { tabPositions ->
            CustomTabIndicator(
                tabPosition = tabPositions,
                pagerState = pagerState
            )
        },
        divider = { },
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                modifier = Modifier.padding(horizontal = 8.dp),
                selected = pagerState.currentPage == index,
                onClick = { onTabSelected(index) }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 2.dp),
                    text = category
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTabIndicator(
    tabPosition: List<TabPosition>,
    pagerState: PagerState
) {
    val transition = updateTransition(
        targetState = pagerState.currentPage,
        label = null
    )
    val indicatorStart by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 400f)
            } else spring(dampingRatio = 1f, stiffness = 1500f)
        },
        label = ""
    ) { index ->
        tabPosition[index].left
    }
    val indicatorEnd by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                spring(dampingRatio = 1f, stiffness = 1500f)
            } else spring(dampingRatio = 1f, stiffness = 400f)
        },
        label = ""
    ) { index ->
        tabPosition[index].right
    }

    Box(
        modifier = Modifier
            .offset(x = indicatorStart)
            .wrapContentSize(align = Alignment.BottomStart)
            .width(indicatorEnd - indicatorStart)
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f),
                shape = RoundedCornerShape(8.dp)
            )
            .zIndex(1f)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CategoryTabRowPreview() {
    ArticleTheme {
        val categories = listOf("General", "Sports")
        CategoryTabRow(
            pagerState = rememberPagerState { categories.size },
            categories = categories,
            onTabSelected = {}
        )
    }
}