package com.makeevrserg.koleso.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.defaultComponentContext
import com.makeevrserg.koleso.android.application.App.Companion.toApp
import com.makeevrserg.koleso.feature.koleso.dialog.presentation.DefaultDialogComponent
import com.makeevrserg.koleso.feature.koleso.dialog.ui.DialogContent
import com.makeevrserg.koleso.feature.koleso.root.presentation.DefaultRootKolesoComponent
import com.makeevrserg.koleso.feature.koleso.root.ui.KolesoScreen
import com.makeevrserg.koleso.feature.root.di.RootModule
import com.makeevrserg.koleso.service.core.ui.theme.CustomTheme

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    private val rootModule: RootModule
        get() = application.toApp().rootModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setTheme(R.style.AppTheme)

        val rootComponentContext = defaultComponentContext()

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

        setContent {
            CustomTheme {
                KolesoScreen(rootKolesoComponent)

                DialogContent(dialogComponent)
            }
        }
    }
}
