package com.makeevrserg.koleso.service.db.api.di

import com.makeevrserg.koleso.service.db.api.ParticipantsApi
import com.makeevrserg.koleso.service.db.api.ParticipantsApiImpl
import com.makeevrserg.koleso.service.db.api.PlayerQueries
import com.makeevrserg.koleso.service.db.api.di.factory.DbDriverFactory
import com.makeevrserg.koleso.service.db.api.util.map
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

interface DbApiModule {
    val participantsApi: ParticipantsApi

    class Default(platformConfiguration: PlatformConfiguration) : DbApiModule {
        override val participantsApi: ParticipantsApi by lazy {
            val driver = DbDriverFactory(platformConfiguration).create()
            val querries = driver.map { PlayerQueries(it) }
            ParticipantsApiImpl(querries)
        }
    }
}