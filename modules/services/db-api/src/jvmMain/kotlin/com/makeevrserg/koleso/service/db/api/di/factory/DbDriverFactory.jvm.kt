@file:Suppress("MatchingDeclarationName")

package com.makeevrserg.koleso.service.db.api.di.factory

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.makeevrserg.koleso.db.api.Database
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration
import java.io.File

actual class DbDriverFactory actual constructor(platformConfiguration: PlatformConfiguration) {
    actual fun create(): Deferred<SqlDriver> {
        val file = File("./temp/myDatabase.db")
        if (!file.exists()) {
            file.parentFile.mkdirs()
            file.createNewFile()
        }
        val driver = JdbcSqliteDriver(url = "jdbc:sqlite:${file.absolutePath}")

        return runBlocking {
            async {
                Database.Schema.awaitCreate(driver)
                driver
            }
        }
    }
}
