package com.makeevrserg.koleso.service.db.api.storage

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.astrainteractive.klibs.kstorage.StateFlowMutableStorageValue
import ru.astrainteractive.klibs.kstorage.api.StateFlowMutableStorageValue

@Suppress("FunctionNaming")
inline fun <reified T : Any> JsonModelStorage(
    settings: Settings,
    key: String,
    json: Json
) = StateFlowMutableStorageValue(
    default = null,
    loadSettingsValue = block@{
        runCatching {
            val string = settings.getString(key, "")
            json.decodeFromString<T>(string)
        }.onFailure(Throwable::printStackTrace).getOrNull()
    },
    saveSettingsValue = block@{ value ->
        if (value == null) {
            settings.remove(key)
            return@block
        }
        runCatching {
            val string = value.let(json::encodeToString)
            settings.putString(key, string)
        }.onFailure(Throwable::printStackTrace)
    }
)
