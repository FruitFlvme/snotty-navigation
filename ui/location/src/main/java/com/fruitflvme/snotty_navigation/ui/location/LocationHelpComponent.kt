package com.fruitflvme.snotty_navigation.ui.location

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.fruitflvme.snotty_navigation.ui.design.components.HelpComponent
import com.fruitflvme.snotty_navigation.ui.design.theme.SnottyTheme

@Composable
fun LocationHelpComponent(
    contentPadding: PaddingValues,
    onUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    HelpComponent(
        title = { Text(text = stringResource(id = R.string.ui_location_help_title)) },
        markdownRes = R.raw.ui_location_help,
        contentPadding = contentPadding,
        onUpClick = onUpClick,
        modifier = modifier
    )
}

@PreviewLightDark
@Composable
private fun Preview() {
    SnottyTheme {
        LocationHelpComponent(PaddingValues(), onUpClick = {})
    }
}