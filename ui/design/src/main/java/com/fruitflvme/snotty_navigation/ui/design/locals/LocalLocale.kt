package com.fruitflvme.snotty_navigation.ui.design.locals

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.core.os.LocaleListCompat

val LocalLocale = staticCompositionLocalOf { checkNotNull(LocaleListCompat.getDefault()[0]) }