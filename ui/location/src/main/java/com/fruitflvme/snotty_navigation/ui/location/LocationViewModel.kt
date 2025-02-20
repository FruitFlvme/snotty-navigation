package com.fruitflvme.snotty_navigation.ui.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fruitflvme.snotty_navigation.data.location.Locator
import com.fruitflvme.snotty_navigation.data.settings.SettingsRepository
import com.fruitflvme.snotty_navigation.model.location.Location
import com.fruitflvme.snotty_navigation.ui.core.State
import com.fruitflvme.snotty_navigation.ui.core.asStates
import com.fruitflvme.snotty_navigation.ui.core.flow.ForViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    locator: Locator,
    settings: SettingsRepository
) : ViewModel() {

    val location: StateFlow<State<Location, Unit>> = locator.location
        .asStates()
        .stateIn(viewModelScope, SharingStarted.ForViewModel, initialValue = State.Loading)

    val settings: StateFlow<State<Settings, Unit>> =
        combine(
            settings.coordinatesFormat,
            settings.units,
            settings.locationAccuracyVisibility
        ) { coordinatesFormat, units, accuracyVisibility ->
            Settings(coordinatesFormat, units, accuracyVisibility)
        }.asStates()
            .stateIn(viewModelScope, SharingStarted.ForViewModel, initialValue = State.Loading)
}