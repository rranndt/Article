package dev.rranndt.article.presentation.bookmark

import dev.rranndt.article.domain.model.Article

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Mon, September 11
 */
sealed class BookmarkEvent {
    data class OnHeadlineNewsClick(val article: Article) : BookmarkEvent()
    data class DeleteArticle(val article: Article) : BookmarkEvent()
    data object GetArticles : BookmarkEvent()
    data object ClearArticle : BookmarkEvent()
}
