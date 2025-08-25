package com.formation.composeformation.domain.utils.extension

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

enum class PresentationDateTimePattern(val value: String) {
    DayMonthYearShortFormat("dd LLL yyyy")
}

//-----------------------------------------------------------------------------------------------
// LocalDate
//-----------------------------------------------------------------------------------------------
fun LocalDate.toFormattedString(pattern: PresentationDateTimePattern): String {
    val formatter = DateTimeFormatter.ofPattern(pattern.value, Locale.FRANCE)
    return this.format(formatter)
}
