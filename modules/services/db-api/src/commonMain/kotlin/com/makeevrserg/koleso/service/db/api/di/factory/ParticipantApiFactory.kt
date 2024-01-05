package com.makeevrserg.koleso.service.db.api.di.factory

import com.makeevrserg.koleso.service.db.api.ParticipantsApi
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

expect class ParticipantApiFactory(platformConfiguration: PlatformConfiguration) {
    fun create(): ParticipantsApi
}
