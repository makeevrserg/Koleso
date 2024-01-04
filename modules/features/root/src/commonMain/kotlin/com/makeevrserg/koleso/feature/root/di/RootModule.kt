package com.makeevrserg.koleso.feature.root.di

import com.makeevrserg.koleso.service.core.CoroutineFeature
import com.makeevrserg.koleso.service.db.api.di.DbApiModule
import kotlinx.coroutines.CoroutineScope
import ru.astrainteractive.klibs.kdi.Lateinit
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

interface RootModule {
    val platformConfiguration: Lateinit<PlatformConfiguration>
    val mainScope: CoroutineScope

    val dbApiModule: DbApiModule

    class Default : RootModule {
        override val platformConfiguration = Lateinit<PlatformConfiguration>()
        override val mainScope: CoroutineScope = CoroutineFeature.Default()

        override val dbApiModule: DbApiModule by lazy {
            DbApiModule.Default(platformConfiguration.value, mainScope)
        }
    }
}
