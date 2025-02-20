package com.fruitflvme.snotty_navigation.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fruitflvme.snotty_navigation.data.settings.SettingsRepository
import com.fruitflvme.snotty_navigation.model.core.measurement.Units
import com.fruitflvme.snotty_navigation.model.settings.CoordinatesFormat
import com.fruitflvme.snotty_navigation.model.settings.LocationAccuracyVisibility
import com.fruitflvme.snotty_navigation.model.settings.Theme
import com.fruitflvme.snotty_navigation.ui.core.flow.ForViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    val theme: StateFlow<Theme?> = settingsRepository.theme
        .stateIn(viewModelScope, SharingStarted.ForViewModel, initialValue = null)

    fun onThemeChange(theme: Theme) {
        viewModelScope.launch { settingsRepository.setTheme(theme) }
    }

    val units: StateFlow<Units?> = settingsRepository.units
        .stateIn(viewModelScope, SharingStarted.ForViewModel, initialValue = null)

    fun onUnitsChange(units: Units) {
        viewModelScope.launch { settingsRepository.setUnits(units) }
    }

    val coordinatesFormat: StateFlow<CoordinatesFormat?> = settingsRepository.coordinatesFormat
        .stateIn(viewModelScope, SharingStarted.ForViewModel, initialValue = null)

    fun onCoordinatesFormatChange(coordinatesFormat: CoordinatesFormat) {
        viewModelScope.launch { settingsRepository.setCoordinatesFormat(coordinatesFormat) }
    }

    val locationAccuracyVisibility: StateFlow<LocationAccuracyVisibility?> =
        settingsRepository.locationAccuracyVisibility
            .stateIn(viewModelScope, SharingStarted.ForViewModel, initialValue = null)

    fun onLocationAccuracyVisibilityChange(visibility: LocationAccuracyVisibility) {
        viewModelScope.launch { settingsRepository.setLocationAccuracyVisibility(visibility) }
    }
}