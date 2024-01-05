@file:Suppress("Filename")

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.makeevrserg.koleso.feature.koleso.dialog.presentation.DefaultDialogComponent
import com.makeevrserg.koleso.feature.koleso.dialog.ui.DialogContent
import com.makeevrserg.koleso.feature.koleso.root.presentation.DefaultRootKolesoComponent
import com.makeevrserg.koleso.feature.koleso.root.ui.KolesoScreen
import com.makeevrserg.koleso.service.core.ui.theme.CustomTheme
import org.jetbrains.skiko.wasm.onWasmReady
import ru.astrainteractive.klibs.mikro.platform.DefaultJSPlatformConfiguration

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        val rootModule = com.makeevrserg.koleso.feature.root.di.RootModule.Default().apply {
            platformConfiguration.initialize { DefaultJSPlatformConfiguration() }
        }
        val lifecycle = LifecycleRegistry()
        val rootComponentContext = DefaultComponentContext(lifecycle)

        val dialogComponent = DefaultDialogComponent(
            componentContext = rootComponentContext.childContext("dialogComponent"),
            participantsApi = rootModule.dbApiModule.participantsApi,
            mainScope = rootModule.mainScope
        )

        val rootKolesoComponent = DefaultRootKolesoComponent(
            componentContext = rootComponentContext,
            dialogComponent = dialogComponent,
            participantsApi = rootModule.dbApiModule.participantsApi
        )
        lifecycle.resume()
        CanvasBasedWindow("Koleso") {
            CustomTheme {
                KolesoScreen(rootKolesoComponent)

                DialogContent(dialogComponent)
            }
        }
    }
}
