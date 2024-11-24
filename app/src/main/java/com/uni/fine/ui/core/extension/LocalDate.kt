package com.uni.fine.ui.core.extension

import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

internal fun String.toLocalDate(): LocalDate {
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val offsetDateTime = OffsetDateTime.parse(this, formatter)
    return offsetDateTime.toLocalDate()
}

fun LocalDate.convertLocalDateTimeToUkrainianFormat(): String {
    val outputFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale("uk"))
    return this.format(outputFormat)
}