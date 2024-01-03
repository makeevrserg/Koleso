package com.makeevrserg.koleso.desktop

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.makeevrserg.koleso.feature.koleso.root.presentation.DefaultRootKolesoComponent
import com.makeevrserg.koleso.feature.koleso.root.ui.KolesoScreen

fun main() = application {
    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle)

    val rootKolesoComponent = runOnUiThread {
        DefaultRootKolesoComponent(
            componentContext = rootComponentContext,
        )
    }

    Window(onCloseRequest = ::exitApplication) {
        MaterialTheme {
            KolesoScreen(rootKolesoComponent)
        }
    }
}
