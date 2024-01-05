@file:Suppress("MatchingDeclarationName")

package com.makeevrserg.koleso.service.db.api.di.factory

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.makeevrserg.koleso.db.api.Database
import com.makeevrserg.koleso.service.db.api.ParticipantsApi
import com.makeevrserg.koleso.service.db.api.SQLiteParticipantsApi
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

actual class ParticipantApiFactory actual constructor(private val platformConfiguration: PlatformConfiguration) {
    actual fun create(): ParticipantsApi {
        val driver = AndroidSqliteDriver(
            schema = Database.Schema.synchronous(),
            context = platformConfiguration.applicationContext,
            name = "databas2e.db"
        )
        return SQLiteParticipantsApi(driver)
    }
}
