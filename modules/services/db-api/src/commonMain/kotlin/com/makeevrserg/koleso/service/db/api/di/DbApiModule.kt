package com.makeevrserg.koleso.service.db.api.di

import com.makeevrserg.koleso.service.db.api.ParticipantsApi
import com.makeevrserg.koleso.service.db.api.di.factory.ParticipantApiFactory
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

interface DbApiModule {
    val participantsApi: ParticipantsApi

    class Default(platformConfiguration: PlatformConfiguration) : DbApiModule {
        override val participantsApi: ParticipantsApi by lazy {
            ParticipantApiFactory(platformConfiguration).create()
        }
    }
}
