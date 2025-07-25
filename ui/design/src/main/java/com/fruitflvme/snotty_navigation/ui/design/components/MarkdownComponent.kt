package com.fruitflvme.snotty_navigation.ui.design.components

import android.content.res.ColorStateList
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.viewinterop.AndroidView
import com.fruitflvme.snotty_navigation.ui.design.theme.SnottyTheme
import io.noties.markwon.Markwon

private const val BASE_TEXT_SIZE_SP = 16f

@Composable
fun MarkdownComponent(
    content: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        val textColor = LocalContentColor.current.toArgb()
        AndroidView(
            factory = {
                TextView(it).apply {
                    tag = Markwon.create(it)
                    textSize = BASE_TEXT_SIZE_SP
                    setTextColor(ColorStateList.valueOf(textColor))
                }
            },
            update = { (it.tag as Markwon).setMarkdown(it, content) },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    SnottyTheme {
        MarkdownComponent(content = "**Title** Body")
    }
}