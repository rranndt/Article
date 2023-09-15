package dev.rranndt.article.domain.usecase

import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.domain.repository.NewsRepository
import javax.inject.Inject

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Wed, September 13
 */
class UpdateBookmarkArticle @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(article: Article, isBookmark: Boolean) {
        newsRepository.updateBookmarkArticle(article, isBookmark)
    }
}