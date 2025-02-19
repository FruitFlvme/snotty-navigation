package com.fruitflvme.snotty_navigation.ui.core

import com.fruitflvme.snotty_navigation.ui.core.format.DateTimeFormatter
import com.fruitflvme.snotty_navigation.ui.core.format.SystemDateTimeFormatter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
interface UiCoreModule {

    @Binds
    fun dateTimeFormatter(systemDateTimeFormatter: SystemDateTimeFormatter): DateTimeFormatter
}