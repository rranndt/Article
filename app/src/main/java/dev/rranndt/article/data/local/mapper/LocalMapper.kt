package dev.rranndt.article.data.local.mapper

import dev.rranndt.article.data.local.entity.ArticleEntity
import dev.rranndt.article.data.local.entity.SourceEntity
import dev.rranndt.article.domain.model.Article
import dev.rranndt.article.domain.model.Source

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Mon, September 11
 */
fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        source = source.toSourceEntity(),
        author = author ?: "",
        title = title ?: "",
        description = description ?: "",
        urlToImage = urlToImage ?: "",
        publishedAt = publishedAt ?: "",
        content = content ?: "",
        url = url ?: "",
        isBookmark = isBookmark
    )
}

fun Source.toSourceEntity(): SourceEntity {
    return SourceEntity(
        id = id ?: "",
        name = name ?: ""
    )
}

fun ArticleEntity.toArticle(): Article {
    return Article(
        source = source.toSource(),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
        isBookmark = isBookmark
    )
}

fun toArticle(input: List<ArticleEntity>): List<Article> {
    return input.map {
        Article(
            source = it.source.toSource(),
            author = it.author,
            title = it.title,
            description = it.description,
            url = it.url,
            urlToImage = it.urlToImage,
            publishedAt = it.publishedAt,
            content = it.content,
            isBookmark = it.isBookmark
        )
    }
}

fun SourceEntity.toSource(): Source {
    return Source(
        id = id,
        name = name
    )
}