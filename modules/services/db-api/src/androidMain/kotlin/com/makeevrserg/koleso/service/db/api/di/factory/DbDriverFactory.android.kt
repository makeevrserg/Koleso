@file:Suppress("MatchingDeclarationName")

package com.makeevrserg.koleso.service.db.api.di.factory

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.makeevrserg.koleso.db.api.Database
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

actual class DbDriverFactory actual constructor(private val platformConfiguration: PlatformConfiguration) {
    actual fun create(): Deferred<SqlDriver> {
        val driver = AndroidSqliteDriver(
            schema = Database.Schema.synchronous(),
            context = platformConfiguration.applicationContext,
            name = "databas2e.db"
        )
        return runBlocking { async { driver } }
    }
}
