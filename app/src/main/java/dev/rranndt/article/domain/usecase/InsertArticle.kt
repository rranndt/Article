package dev.rranndt.article.domain.usecase

import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.domain.repository.NewsRepository
import javax.inject.Inject

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Mon, September 11
 */
class InsertArticle @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(article: Article) {
        newsRepository.insertArticle(article = article)
    }
}