package com.fruitflvme.snotty_navigation.ui.location

import androidx.compose.runtime.Immutable
import com.fruitflvme.snotty_navigation.model.core.measurement.Units
import com.fruitflvme.snotty_navigation.model.settings.CoordinatesFormat
import com.fruitflvme.snotty_navigation.model.settings.LocationAccuracyVisibility

@Immutable
data class Settings(
    val coordinatesFormat: CoordinatesFormat,
    val units: Units,
    val accuracyVisibility: LocationAccuracyVisibility
)