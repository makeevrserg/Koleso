@file:Suppress("MatchingDeclarationName")

package com.makeevrserg.koleso.service.db.api.di.factory

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.makeevrserg.koleso.db.api.Database
import com.makeevrserg.koleso.service.db.api.ParticipantsApi
import com.makeevrserg.koleso.service.db.api.SQLiteParticipantsApi
import kotlinx.coroutines.runBlocking
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration
import java.io.File

actual class ParticipantApiFactory actual constructor(platformConfiguration: PlatformConfiguration) {
    actual fun create(): ParticipantsApi {
        val file = File("./temp/myDatabase.db")
        if (!file.exists()) {
            file.parentFile.mkdirs()
            file.createNewFile()
        }
        val driver = JdbcSqliteDriver(url = "jdbc:sqlite:${file.absolutePath}").also {
            runBlocking { Database.Schema.awaitCreate(it) }
        }
        return SQLiteParticipantsApi(driver)
    }
}
