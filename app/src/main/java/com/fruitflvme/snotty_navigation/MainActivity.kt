package com.fruitflvme.snotty_navigation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.fruitflvme.snotty_navigation.ui.core.format.DateTimeFormatter
import com.fruitflvme.snotty_navigation.ui.design.locals.LocalDateTimeFormatter
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dateTimeFormatter: DateTimeFormatter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalDateTimeFormatter provides dateTimeFormatter) {
                val widthSizeClass = calculateWindowSizeClass(activity = this).widthSizeClass
                MainComponent(
                    navHostController = rememberNavController(),
                    windowWidthSizeClass = widthSizeClass
                )
            }
        }
    }
}