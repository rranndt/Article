package dev.rranndt.article.domain.usecase

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
data class NewsUseCases(
    val getHeadlineNews: GetHeadlineNews,
    val searchNews: SearchNews,
    val insertArticle: InsertArticle,
    val deleteArticle: DeleteArticle,
    val getArticle: GetArticle,
    val getArticles: GetArticles,
    val clearArticle: ClearArticle,
    val updateBookmarkArticle: UpdateBookmarkArticle
)
