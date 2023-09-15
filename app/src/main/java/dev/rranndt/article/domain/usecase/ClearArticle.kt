package dev.rranndt.article.domain.usecase

import dev.rranndt.article.domain.repository.NewsRepository
import javax.inject.Inject

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Tue, September 12
 */
class ClearArticle @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke() {
        newsRepository.clearArticle()
    }
}