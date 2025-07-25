package com.fruitflvme.snotty_navigation.ui.location.format

import com.fruitflvme.snotty_navigation.model.core.measurement.Coordinates
import com.fruitflvme.snotty_navigation.model.core.measurement.Distance
import com.fruitflvme.snotty_navigation.model.core.measurement.MgrsCoordinates.Companion.EASTING_NORTHING_LENGTH
import com.fruitflvme.snotty_navigation.model.core.measurement.MgrsCoordinates.Companion.EASTING_NORTHING_PAD_CHAR
import kotlin.math.roundToInt

/**
 * Formats coordinates in the Military Grid Reference System (MGRS)
 */
class MgrsFormatter : CoordinatesFormatter {

    override fun formatForDisplay(coordinates: Coordinates?): List<String?> =
        coordinates?.asMgrsCoordinates()?.let { mgrsCoordinates ->
            with(mgrsCoordinates) {
                listOf(
                    "${gridZoneDesignator} $gridSquareID",
                    EASTING_NORTHING_FORMAT.format(easting.inRoundedMeters()),
                    EASTING_NORTHING_FORMAT.format(northing.inRoundedMeters()),
                )
            }
        } ?: List(FORMAT_DISPLAY_LINE_COUNT) { null }

    override fun formatForCopy(coordinates: Coordinates): String =
        coordinates.asMgrsCoordinates().toString()
}

private fun Distance.inRoundedMeters(): Int = inMeters().magnitude.roundToInt()

private const val EASTING_NORTHING_FORMAT =
    "%${EASTING_NORTHING_PAD_CHAR}${EASTING_NORTHING_LENGTH}d"
private const val FORMAT_DISPLAY_LINE_COUNT = 3