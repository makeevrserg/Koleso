package com.makeevrserg.koleso.desktop

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.makeevrserg.koleso.feature.koleso.DefaultWheelComponent
import com.makeevrserg.koleso.feature.koleso.WheelComponent

@Composable
fun WheelButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("Wheel!")
    }
}

@Composable
fun Wheel(wheelComponent: WheelComponent) {
    val model by wheelComponent.model.collectAsState()
    Column {
        Text("${model}")
        when (val model = model) {
            WheelComponent.Model.Pending -> {
                Box(Modifier.size(64.dp).background(Color.Red))
                WheelButton(wheelComponent::startWheel)
            }

            is WheelComponent.Model.Wheeled -> {
                val degree by animateFloatAsState(model.degree)
                Box(Modifier.size(64.dp).rotate(degree).background(Color.Red))
                WheelButton(wheelComponent::startWheel)
            }

            is WheelComponent.Model.Wheeling -> {
                val degree by animateFloatAsState(model.degree)
                Box(Modifier.size(64.dp).rotate(degree).background(Color.Red))
            }
        }
    }
}

fun main() = application {

    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle)
    val wheelComponent = runOnUiThread {
        DefaultWheelComponent(
            componentContext = rootComponentContext,
        )
    }

    Window(onCloseRequest = ::exitApplication) {
        MaterialTheme {
            Wheel(wheelComponent)
        }
    }
}
