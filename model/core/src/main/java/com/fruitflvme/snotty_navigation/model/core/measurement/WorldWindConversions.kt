package com.fruitflvme.snotty_navigation.model.core.measurement

import earth.worldwind.geom.Angle.Companion.degrees

internal fun Angle.asWorldWindAngle(): earth.worldwind.geom.Angle = inDegrees().magnitude.degrees

internal fun earth.worldwind.geom.Angle.asAngle(): Angle = Angle(inDegrees, Angle.Unit.DEGREES)