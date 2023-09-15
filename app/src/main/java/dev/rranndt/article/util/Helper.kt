package dev.rranndt.article.util

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

/**
 * Created by Android Studio Giraffe
 * User: rranndt
 * Date: Sat, September 09
 */
fun parseStringToLocalDateTime(inputDateTime: String): String {
    val inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val outputFormatter = DateTimeFormatter
        .ofLocalizedDate(FormatStyle.LONG)
        .withLocale(Locale.getDefault())
    val dateString = try {
        val dateTime = OffsetDateTime.parse(inputDateTime, inputFormatter)
        dateTime.format(outputFormatter)
    } catch (e: Exception) {
        ""
    }
    return dateString
}