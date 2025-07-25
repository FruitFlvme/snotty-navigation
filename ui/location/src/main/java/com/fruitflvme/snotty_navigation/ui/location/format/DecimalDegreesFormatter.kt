package com.fruitflvme.snotty_navigation.ui.location.format

import android.content.Context
import com.fruitflvme.snotty_navigation.model.core.measurement.Angle
import com.fruitflvme.snotty_navigation.model.core.measurement.Coordinates
import com.fruitflvme.snotty_navigation.ui.location.R
import java.util.Locale

/**
 * Formats coordinates as decimal degrees
 */
class DecimalDegreesFormatter(
    private val context: Context,
    private val locale: Locale
) : CoordinatesFormatter {

    override fun formatForDisplay(coordinates: Coordinates?): List<String?> {
        val geodeticCoordinates = coordinates?.asGeodeticCoordinates()
        return listOf(
            geodeticCoordinates?.latitude?.let { FORMAT_DISPLAY.format(it) },
            geodeticCoordinates?.longitude?.let { FORMAT_DISPLAY.format(it) }
        )
    }

    override fun formatForCopy(coordinates: Coordinates): String {
        val geodeticCoordinates = coordinates.asGeodeticCoordinates()
        return context.getString(
            R.string.ui_location_coordinates_copy_format_dd,
            FORMAT_COPY.format(geodeticCoordinates.latitude),
            FORMAT_COPY.format(geodeticCoordinates.longitude)
        )
    }

    private fun String.format(angle: Angle): String = format(locale, angle.inDegrees().magnitude)
}

private const val FORMAT_COPY = "%.5f"
private const val FORMAT_DISPLAY = "%10.5f"