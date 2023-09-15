package dev.rranndt.article.presentation.search

import dev.rranndt.article.domain.model.Article

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sun, September 10
 */
sealed class SearchEvent {
    data class OnHeadlineNewsClick(val article: Article) : SearchEvent()
    data class OnSearchQueryChanged(val searchQuery: String) : SearchEvent()
    data class OnSearchQueryFilled(val query: String) : SearchEvent()
    data object OnCloseIconClick : SearchEvent()
}