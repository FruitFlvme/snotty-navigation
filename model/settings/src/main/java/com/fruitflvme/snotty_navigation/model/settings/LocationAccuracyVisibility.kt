package com.fruitflvme.snotty_navigation.model.settings

/**
 * Visibility of location accuracy information
 */
enum class LocationAccuracyVisibility {

    /**
     * Show location accuracy in the UI if possible (API 26+)
     */
    SHOW,

    /**
     * Hide location accuracy in the UI
     */
    HIDE
}