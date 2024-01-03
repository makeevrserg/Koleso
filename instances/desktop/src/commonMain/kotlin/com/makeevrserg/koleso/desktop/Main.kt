package com.makeevrserg.koleso.desktop

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.makeevrserg.koleso.feature.koleso.dialog.presentation.DefaultDialogComponent
import com.makeevrserg.koleso.feature.koleso.dialog.ui.DialogContent
import com.makeevrserg.koleso.feature.koleso.root.presentation.DefaultRootKolesoComponent
import com.makeevrserg.koleso.feature.koleso.root.ui.KolesoScreen
import com.makeevrserg.koleso.service.theme.CustomTheme

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

    val icon = painterResource("empire_astra_trans.png")
    Tray(
        icon = icon,
        menu = {
            Item("Close", onClick = ::exitApplication)
        }
    )

    Window(
        onCloseRequest = ::exitApplication,
        icon = icon,
        title = "Koleso: EmpireProjekt"
    ) {
        CustomTheme {
            KolesoScreen(rootKolesoComponent)

            DialogContent(dialogComponent)
        }
    }
}
