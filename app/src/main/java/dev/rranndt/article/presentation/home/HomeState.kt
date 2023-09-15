package dev.rranndt.article.presentation.home

import dev.rranndt.article.domain.model.Article

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
data class HomeState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedArticle: Article? = null,
    val category: String = "General",
    val searchQuery: String = ""
)
