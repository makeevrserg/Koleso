package com.makeevrserg.koleso.android.application

import android.app.Application
import com.makeevrserg.koleso.feature.root.di.RootModule
import ru.astrainteractive.klibs.mikro.platform.DefaultAndroidPlatformConfiguration

class App : Application() {
    val rootModule by lazy {
        RootModule.Default().apply {
            platformConfiguration.initialize { DefaultAndroidPlatformConfiguration(applicationContext) }
        }
    }

    companion object {
        fun Application.toApp() = this as App
    }
}
