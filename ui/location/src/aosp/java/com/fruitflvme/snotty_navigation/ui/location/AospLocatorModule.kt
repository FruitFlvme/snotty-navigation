package com.fruitflvme.snotty_navigation.ui.location

import android.content.Context
import android.location.LocationManager
import com.fruitflvme.snotty_navigation.data.location.AospLocator
import com.fruitflvme.snotty_navigation.data.location.Locator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
interface AospLocatorModule {

    @Binds
    fun aospLocator(aospLocator: AospLocator): Locator

    companion object {

        @Provides
        fun locationManager(
            @ApplicationContext context: Context
        ): LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }
}
