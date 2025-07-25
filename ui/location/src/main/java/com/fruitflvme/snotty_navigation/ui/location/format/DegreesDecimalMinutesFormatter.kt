package com.fruitflvme.snotty_navigation.ui.location.format

import android.content.Context
import android.location.Location.FORMAT_MINUTES
import android.location.Location.convert
import com.fruitflvme.snotty_navigation.model.core.measurement.Angle
import com.fruitflvme.snotty_navigation.model.core.measurement.Coordinates
import com.fruitflvme.snotty_navigation.ui.location.R
import java.util.Locale

/**
 * Formats coordinates as degrees and decimal minutes
 */
class DegreesDecimalMinutesFormatter(
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
            R.string.ui_location_coordinates_copy_format_ddm,
            FORMAT_COPY.format(geodeticCoordinates.latitude),
            FORMAT_COPY.format(geodeticCoordinates.longitude)
        )
    }

    private fun String.format(angle: Angle): String {
        val components = convert(angle.inDegrees().magnitude, FORMAT_MINUTES).split(':')
        val degrees = components[0].toInt()
        val minutes = components[1].normalizeDecimalSeparator().toFloat()
        return format(locale, degrees, minutes)
    }
}

private const val FORMAT_COPY = "%1d° %2$.3f'"
private const val FORMAT_DISPLAY = "%1\$4d° %2\$06.3f'"