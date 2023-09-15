package dev.rranndt.article.presentation.bookmark

import dev.rranndt.article.domain.model.Article

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Mon, September 11
 */
data class BookmarkState(
    val articles: List<Article> = emptyList(),
    val selectedArticle: Article? = null,
)
