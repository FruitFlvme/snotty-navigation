package com.fruitflvme.snotty_navigation.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fruitflvme.snotty_navigation.data.settings.SettingsRepository
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
        viewModelScope.launch {
            settingsRepository.setTheme(theme)
        }
    }
}