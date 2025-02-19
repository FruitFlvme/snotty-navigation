package com.fruitflvme.snotty_navigation.ui.settings

import android.content.Context
import com.fruitflvme.snotty_navigation.data.settings.DataStoreSettingsRepository
import com.fruitflvme.snotty_navigation.data.settings.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SettingsModule {
    @Provides
    @Singleton
    fun settingsRepository(
        @ApplicationContext context: Context
    ): SettingsRepository = DataStoreSettingsRepository(context)
}