package com.fruitflvme.snotty_navigation.model.location

import com.fruitflvme.snotty_navigation.model.core.measurement.Angle
import com.fruitflvme.snotty_navigation.model.core.measurement.Coordinates
import com.fruitflvme.snotty_navigation.model.core.measurement.Distance
import com.fruitflvme.snotty_navigation.model.core.measurement.Speed
import kotlinx.datetime.Instant

/**
 * Location somewhere on Earth, represented in a decimal degrees latitude, longitude, altitude, and
 * timestamp (among other metadata).
 *
 * @property timestamp Instant at which the location was determined
 * @property coordinates Geographic coordinates of the location
 * @property horizontalAccuracy Horizontal accuracy of the location or `null` if unknown
 * @property bearing Bearing (direction of movement) or `null` if unknown
 * @property bearingAccuracy Accuracy of the bearing or `null` if unknown
 * @property altitude Altitude above the WGS84 ellipsoid or `null` if unknown
 * @property altitudeAccuracy Accuracy of the altitude or `null` if unknown
 * @property magneticDeclination Magnetic declination (i.e. angle between true north and magnetic
 * north) at the location or `null` if unknown
 * @property speed Speed or `null` if unknown
 * @property speedAccuracy Accuracy of the speed or `null` if unknown
 */
data class Location(
    val timestamp: Instant,
    val coordinates: Coordinates,
    val horizontalAccuracy: Distance? = null,
    val bearing: Angle? = null,
    val bearingAccuracy: Angle? = null,
    val altitude: Distance? = null,
    val altitudeAccuracy: Distance? = null,
    val magneticDeclination: Angle? = null,
    val speed: Speed? = null,
    val speedAccuracy: Speed? = null
)