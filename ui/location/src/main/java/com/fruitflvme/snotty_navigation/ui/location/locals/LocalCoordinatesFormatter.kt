package com.fruitflvme.snotty_navigation.ui.location.locals

import androidx.compose.runtime.staticCompositionLocalOf
import com.fruitflvme.snotty_navigation.ui.location.format.CoordinatesFormatter

val LocalCoordinatesFormatter = staticCompositionLocalOf<CoordinatesFormatter> {
    error("No coordinates formatter has been specified")
}