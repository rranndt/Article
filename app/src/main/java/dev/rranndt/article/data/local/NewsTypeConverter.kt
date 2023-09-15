package dev.rranndt.article.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import dev.rranndt.article.data.local.entity.SourceEntity

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sun, September 10
 */
@ProvidedTypeConverter
class NewsTypeConverter {
    @TypeConverter
    fun sourceToString(source: SourceEntity): String {
        return "${source.id}, ${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): SourceEntity {
        return source.split(',').let {
            SourceEntity(id = it[0], name = it[1])
        }
    }
}