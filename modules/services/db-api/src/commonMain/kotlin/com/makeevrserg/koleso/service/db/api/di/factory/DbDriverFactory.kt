package com.makeevrserg.koleso.service.db.api.di.factory

import app.cash.sqldelight.db.SqlDriver
import kotlinx.coroutines.Deferred
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

expect class DbDriverFactory(platformConfiguration: PlatformConfiguration) {
    fun create(): Deferred<SqlDriver>
}
