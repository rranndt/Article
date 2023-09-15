package dev.rranndt.article.data.remote.mapper

import dev.rranndt.article.data.remote.dto.ArticleDto
import dev.rranndt.article.data.remote.dto.SourceDto
import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.domain.model.Source

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
fun ArticleDto.toArticle(): Article {
    return Article(
        source = source.toSource(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
        isBookmark = false
    )
}

fun SourceDto.toSource(): Source {
    return Source(
        id = id,
        name = name
    )
}