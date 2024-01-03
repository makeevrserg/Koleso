package com.makeevrserg.koleso.desktop

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.makeevrserg.koleso.feature.koleso.dialog.presentation.DefaultDialogComponent
import com.makeevrserg.koleso.feature.koleso.dialog.ui.DialogContent
import com.makeevrserg.koleso.feature.koleso.root.presentation.DefaultRootKolesoComponent
import com.makeevrserg.koleso.feature.koleso.root.ui.KolesoScreen
import androidx.compose.material3.MaterialTheme as Material3Theme

fun main() = application {
    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle)

    val dialogComponent = DefaultDialogComponent(
        componentContext = rootComponentContext.childContext("dialogComponent")
    )

    val rootKolesoComponent = runOnUiThread {
        DefaultRootKolesoComponent(
            componentContext = rootComponentContext,
            dialogComponent = dialogComponent,
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

                DialogContent(dialogComponent)
            }
        }
    }
}
