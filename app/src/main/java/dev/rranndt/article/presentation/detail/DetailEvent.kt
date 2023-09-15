package dev.rranndt.article.presentation.detail

import dev.rranndt.article.domain.model.Article

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Mon, September 11
 */
sealed class DetailEvent {
    data class InsertDeleteArticle(val article: Article) : DetailEvent()
    data class UpdateBookmarkArticle(val article: Article, val isBookmark: Boolean) : DetailEvent()
}