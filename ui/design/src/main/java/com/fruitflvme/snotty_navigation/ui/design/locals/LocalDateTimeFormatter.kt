package com.fruitflvme.snotty_navigation.ui.design.locals

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.core.os.LocaleListCompat
import com.fruitflvme.snotty_navigation.ui.core.format.DateTimeFormatter
import com.fruitflvme.snotty_navigation.ui.core.format.SystemDateTimeFormatter

val LocalDateTimeFormatter = staticCompositionLocalOf<DateTimeFormatter> {
    val locale = checkNotNull(LocaleListCompat.getDefault()[0])
    SystemDateTimeFormatter(locale)
}