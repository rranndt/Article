package dev.rranndt.article.presentation.home

import dev.rranndt.article.domain.model.Article

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
sealed class HomeEvent {
    data class OnHeadlineNewsClick(val article: Article) : HomeEvent()
    data class OnCategoryChanged(val category: String) : HomeEvent()
}
