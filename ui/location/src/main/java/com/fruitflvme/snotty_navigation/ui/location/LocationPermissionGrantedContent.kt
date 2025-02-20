package com.fruitflvme.snotty_navigation.ui.location

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.HelpOutline
import androidx.compose.material.icons.rounded.FileCopy
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fruitflvme.snotty_navigation.model.core.measurement.Coordinates
import com.fruitflvme.snotty_navigation.model.location.Location
import com.fruitflvme.snotty_navigation.ui.core.State
import com.fruitflvme.snotty_navigation.ui.design.components.AutoShrinkingText
import com.fruitflvme.snotty_navigation.ui.design.locals.LocalDateTimeFormatter
import com.fruitflvme.snotty_navigation.ui.design.modifier.placeholder
import com.fruitflvme.snotty_navigation.ui.location.locals.LocalCoordinatesFormatter
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun LocationPermissionGrantedContent(
    locationState: State<Location, Unit>,
    settingsState: State<Settings, Unit>,
    snackbarHostState: SnackbarHostState,
    onCopyClick: (Coordinates?) -> Unit,
    onMapClick: (Coordinates?, Instant?) -> Unit,
    onShareClick: (Coordinates?) -> Unit,
    onHelpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val location = locationState.dataOrNull
    val units = settingsState.dataOrNull?.units
    val accuracyVisibility = settingsState.dataOrNull?.accuracyVisibility
    val placeholdersVisible = locationState is State.Loading

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            16.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        CoordinatesComponent(
            coordinates = location?.coordinates,
            timestamp = location?.timestamp,
            snackbarHostState = snackbarHostState,
            onCopyClick = onCopyClick,
            onMapClick = onMapClick,
            onShareClick = onShareClick,
            onHelpClick = onHelpClick
        )
    }
}

@Composable
private fun CoordinatesComponent(
    coordinates: Coordinates?,
    timestamp: Instant?,
    snackbarHostState: SnackbarHostState,
    onCopyClick: (Coordinates?) -> Unit,
    onMapClick: (Coordinates?, Instant?) -> Unit,
    onShareClick: (Coordinates?) -> Unit,
    onHelpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
    ) {
        Coordinates(coordinates = coordinates)
        UpdatedAtText(timestamp = timestamp)
        ButtonRow(
            coordinates = coordinates,
            timestamp = timestamp,
            snackbarHostState = snackbarHostState,
            onCopyClick = onCopyClick,
            onMapClick = onMapClick,
            onShareClick = onShareClick,
            onHelpClick = onHelpClick
        )
    }
}

@Composable
private fun Coordinates(
    coordinates: Coordinates?,
    modifier: Modifier = Modifier
) {
    val formattedCoordinates = LocalCoordinatesFormatter.current.formatForDisplay(coordinates)
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        for (formattedCoordinate in formattedCoordinates) {
            AutoShrinkingText(
                text = formattedCoordinate ?: PLACEHOLDER_SIZING_TEXT,
                modifier = Modifier.placeholder(formattedCoordinate == null),
                textAlign = TextAlign.Center,
                maxLines = 1,
                style = MaterialTheme.typography.displayLarge,
            )
        }
    }
}

@Composable
private fun UpdatedAtText(timestamp: Instant?, modifier: Modifier = Modifier) {
    val localTimestamp = timestamp?.toLocalDateTime(TimeZone.currentSystemDefault())?.time
    Text(
        text = localTimestamp
            ?.let {
                stringResource(
                    R.string.ui_location_updated_at,
                    LocalDateTimeFormatter.current.formatTime(it)
                )
            }
            ?: "",
        style = MaterialTheme.typography.labelLarge,
        modifier = modifier
            .widthIn(min = 128.dp)
            .placeholder(visible = timestamp == null)
    )
}

@Composable
private fun ButtonRow(
    coordinates: Coordinates?,
    timestamp: Instant?,
    snackbarHostState: SnackbarHostState,
    onCopyClick: (Coordinates?) -> Unit,
    onMapClick: (Coordinates?, Instant?) -> Unit,
    onShareClick: (Coordinates?) -> Unit,
    onHelpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MapButton(
            onClick = { onMapClick(coordinates, timestamp) },
            enabled = coordinates != null
        )
        ShareButton(onClick = { onShareClick(coordinates) }, enabled = coordinates != null)
        CopyButton(onClick = { onCopyClick(coordinates) }, enabled = coordinates != null)
        HelpButton(onHelpClick)
    }
}

@Composable
private fun MapButton(
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    IconButton(onClick = onClick, enabled = enabled) {
        Icon(
            Icons.Rounded.Map,
            stringResource(R.string.ui_location_button_map_content_description),
        )
    }
}

@Composable
private fun ShareButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    IconButton(onClick = onClick, modifier = modifier, enabled = enabled) {
        Icon(
            Icons.Rounded.Share,
            stringResource(R.string.ui_location_button_share_content_description),
        )
    }
}

@Composable
private fun CopyButton(onClick: () -> Unit, enabled: Boolean = true) {
    IconButton(onClick = onClick, enabled = enabled) {
        Icon(
            Icons.Rounded.FileCopy,
            stringResource(R.string.ui_location_coordinates_copy_content_description),
        )
    }
}

@Composable
private fun HelpButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.HelpOutline,
            contentDescription = stringResource(R.string.ui_location_button_help_content_description),
        )
    }
}

// This helps us show a reasonably sized placeholder that's adaptable to various text scales. This
// text is not actually visible to the user because a placeholder is shown over it.
private const val PLACEHOLDER_SIZING_TEXT = "-179.99999"
