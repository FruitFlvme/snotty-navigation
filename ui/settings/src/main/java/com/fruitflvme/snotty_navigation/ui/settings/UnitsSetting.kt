package com.fruitflvme.snotty_navigation.ui.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Straighten
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.fruitflvme.snotty_navigation.model.core.measurement.Units
import com.fruitflvme.snotty_navigation.ui.design.theme.SnottyTheme
import com.fruitflvme.snotty_navigation.ui.settings.components.ListSetting

@Composable
fun UnitsSetting(
    value: Units?,
    onValueChange: (Units) -> Unit,
    modifier: Modifier = Modifier
) {
    ListSetting(
        icon = Icons.Rounded.Straighten,
        title = stringResource(R.string.ui_settings_units_title),
        values = Units.entries.toSet(),
        value = value,
        valueName = { units ->
            stringResource(
                when (units) {
                    Units.IMPERIAL -> R.string.ui_settings_units_value_imperial_name
                    Units.METRIC -> R.string.ui_settings_units_value_metric_name
                }
            )
        },
        valuesDialogTitle = stringResource(R.string.ui_settings_units_dialog_title),
        valuesDialogText = null,
        onValueChange = onValueChange,
        modifier = modifier
    )
}

@PreviewLightDark
@Composable
private fun Previews() {
    SnottyTheme {
        Surface {
            UnitsSetting(value = Units.METRIC, onValueChange = {})
        }
    }
}