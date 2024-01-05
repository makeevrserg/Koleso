@file:Suppress("MatchingDeclarationName")

package com.makeevrserg.koleso.service.db.api.di.factory

import com.makeevrserg.koleso.service.db.api.ParticipantsApi
import com.makeevrserg.koleso.service.db.api.SettingsParticipantsApi
import com.russhwolf.settings.StorageSettings
import ru.astrainteractive.klibs.mikro.platform.PlatformConfiguration

actual class ParticipantApiFactory actual constructor(platformConfiguration: PlatformConfiguration) {
    actual fun create(): ParticipantsApi {
        val settings = StorageSettings()
        return SettingsParticipantsApi(settings)
    }
}
