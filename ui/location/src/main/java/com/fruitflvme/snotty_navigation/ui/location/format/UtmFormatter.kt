package com.fruitflvme.snotty_navigation.ui.location.format

import android.content.Context
import com.fruitflvme.snotty_navigation.model.core.measurement.Coordinates
import com.fruitflvme.snotty_navigation.model.core.measurement.Distance
import com.fruitflvme.snotty_navigation.model.core.measurement.Hemisphere
import com.fruitflvme.snotty_navigation.model.core.measurement.UtmCoordinates.Companion.EASTING_NORTHING_LENGTH
import com.fruitflvme.snotty_navigation.model.core.measurement.UtmCoordinates.Companion.EASTING_NORTHING_PAD_CHAR
import com.fruitflvme.snotty_navigation.ui.location.R
import java.util.Locale
import kotlin.math.roundToInt

/**
 * Formats coordinates in the Universal Transverse Mercator (UTM) system
 */
class UtmFormatter(
    private val context: Context,
    private val locale: Locale
) : CoordinatesFormatter {

    override fun formatForDisplay(coordinates: Coordinates?): List<String?> =
        coordinates?.let {
            it.asUtmCoordinates()?.let { utmCoordinates ->
                val hemisphereAbbreviation = context.getString(
                    when (utmCoordinates.hemisphere) {
                        Hemisphere.NORTH -> R.string.ui_location_coordinates_utm_hemisphere_north
                        Hemisphere.SOUTH -> R.string.ui_location_coordinates_utm_hemisphere_south
                    }
                )
                val zoneAndHemisphere = "${utmCoordinates.zone}$hemisphereAbbreviation"
                val easting = EASTING_FORMAT
                    .format(locale, utmCoordinates.easting.inRoundedMeters())
                val northing = NORTHING_FORMAT
                    .format(locale, utmCoordinates.northing.inRoundedMeters())
                listOf(zoneAndHemisphere, easting, northing)
            } ?: List(UTM_COORDINATES_SIZE) { "" }
        } ?: List(UTM_COORDINATES_SIZE) { null }

    override fun formatForCopy(coordinates: Coordinates): String =
        formatForDisplay(coordinates).joinToString(" ")
}

private fun Distance.inRoundedMeters(): Int = inMeters().magnitude.roundToInt()

private const val NUMBER_FORMAT = "%1$$EASTING_NORTHING_PAD_CHAR${EASTING_NORTHING_LENGTH}d"
private const val EASTING_FORMAT = "${NUMBER_FORMAT}m E"
private const val NORTHING_FORMAT = "${NUMBER_FORMAT}m N"
private const val UTM_COORDINATES_SIZE = 3