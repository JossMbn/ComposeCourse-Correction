package com.formation.composecourse.data.utils.extension

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

enum class InputDatePattern(val value: String) {
    YearMonthDayFormat("yyyy-MM-dd")
}

//-----------------------------------------------------------------------------------------------
// LocalDate
//-----------------------------------------------------------------------------------------------
fun String?.toLocalDate(pattern: InputDatePattern = InputDatePattern.YearMonthDayFormat): LocalDate {
    val formatter = DateTimeFormatter.ofPattern(pattern.value, Locale.FRANCE)
    return this.takeIf { !it.isNullOrEmpty() }
        ?.let { LocalDate.parse(it, formatter) } ?: LocalDate.now()
}
