package com.fruitflvme.snotty_navigation.ui.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.fruitflvme.snotty_navigation.model.core.measurement.Units
import com.fruitflvme.snotty_navigation.model.settings.CoordinatesFormat
import com.fruitflvme.snotty_navigation.model.settings.LocationAccuracyVisibility
import com.fruitflvme.snotty_navigation.model.settings.Theme
import com.fruitflvme.snotty_navigation.ui.design.theme.SnottyTheme
import com.fruitflvme.snotty_navigation.ui.design.R as UiDesignR

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun SettingsComponent(
    theme: Theme?,
    onThemeChange: (Theme) -> Unit,
    units: Units?,
    onUnitsChange: (Units) -> Unit,
    coordinatesFormat: CoordinatesFormat?,
    onCoordinatesFormatChange: (CoordinatesFormat) -> Unit,
    contentPadding: PaddingValues,
    locationAccuracyVisibility: LocationAccuracyVisibility?,
    onLocationAccuracyVisibilityChange: (LocationAccuracyVisibility) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text(text = stringResource(R.string.ui_settings_title)) },
                scrollBehavior = scrollBehavior
            )
        },
        contentWindowInsets = WindowInsets(
            top = contentPadding.calculateTopPadding(),
            bottom = contentPadding.calculateBottomPadding()
        )
    ) { innerContentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerContentPadding)
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .verticalScroll(rememberScrollState())
                .padding(dimensionResource(UiDesignR.dimen.ui_design_standard_padding)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            ThemeSetting(
                value = theme,
                onValueChange = onThemeChange,
                modifier = Modifier.fillMaxWidth()
            )
            UnitsSetting(
                value = units,
                onValueChange = onUnitsChange,
                modifier = Modifier.fillMaxWidth()
            )
            CoordinatesFormatSetting(
                value = coordinatesFormat,
                onValueChange = onCoordinatesFormatChange,
                modifier = Modifier.fillMaxWidth()
            )
            LocationAccuracyVisibilitySetting(
                value = locationAccuracyVisibility,
                onValueChange = onLocationAccuracyVisibilityChange,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun LoadingPreviews() {
    SnottyTheme {
        SettingsComponent(
            theme = null,
            onThemeChange = {},
            units = null,
            onUnitsChange = {},
            coordinatesFormat = null,
            onCoordinatesFormatChange = {},
            locationAccuracyVisibility = null,
            onLocationAccuracyVisibilityChange = {},
            contentPadding = PaddingValues()
        )
    }
}

@PreviewLightDark
@Composable
private fun LoadedPreviews() {
    SnottyTheme {
        SettingsComponent(
            theme = null,
            onThemeChange = {},
            units = null,
            onUnitsChange = {},
            coordinatesFormat = null,
            onCoordinatesFormatChange = {},
            locationAccuracyVisibility = null,
            onLocationAccuracyVisibilityChange = {},
            contentPadding = PaddingValues()
        )
    }
}
