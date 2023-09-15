package dev.rranndt.article.domain.usecase

import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Mon, September 11
 */
class GetArticles @Inject constructor(private val newsRepository: NewsRepository) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.getArticles()
    }
}