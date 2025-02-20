package com.fruitflvme.snotty_navigation.ui.design.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.material3.HorizontalDivider as M3HorizontalDivider

@Composable
fun HorizontalDivider(modifier: Modifier = Modifier) {
    M3HorizontalDivider(modifier = modifier.alpha(ALPHA))
}

private const val ALPHA = 0.3f
