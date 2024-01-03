package com.makeevrserg.koleso.desktop

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.makeevrserg.koleso.feature.koleso.root.presentation.DefaultRootKolesoComponent
import com.makeevrserg.koleso.feature.koleso.root.ui.KolesoScreen
import androidx.compose.material3.MaterialTheme as Material3Theme

fun main() = application {
    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle)

    val rootKolesoComponent = runOnUiThread {
        DefaultRootKolesoComponent(
            componentContext = rootComponentContext,
        )
    }

    Window(onCloseRequest = ::exitApplication) {
        Material3Theme(
            colorScheme = darkColorScheme()
        ) {
            MaterialTheme(
                colors = darkColors()
            ) {
                KolesoScreen(rootKolesoComponent)
            }
        }
    }
}
