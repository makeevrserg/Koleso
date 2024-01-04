@file:Suppress("MatchingDeclarationName")

package com.makeevrserg.koleso.service.db.api.di.factory

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import com.makeevrserg.koleso.db.api.Database
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.w3c.dom.Worker
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

actual class DbDriverFactory actual constructor(platformConfiguration: PlatformConfiguration) {
    actual fun create(): Deferred<SqlDriver> = GlobalScope.async {
        val worker = Worker(js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)"""))
        val driver: SqlDriver = WebWorkerDriver(worker)

        Database.Schema.awaitCreate(driver)
        driver

    }
}
