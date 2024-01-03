package com.makeevrserg.koleso.desktop

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.makeevrserg.koleso.feature.koleso.DefaultWheelComponent
import com.makeevrserg.koleso.feature.koleso.WheelComponent
import com.makeevrserg.koleso.feature.koleso.domain.model.WheelConfiguration
import com.makeevrserg.koleso.feature.participants.DefaultParticipantsComponent
import com.makeevrserg.koleso.feature.participants.ParticipantsComponent
import  com.makeevrserg.koleso.feature.participants.domain.model.ArcModel
import com.makeevrserg.koleso.feature.participants.domain.model.ParticipantWithArc
import com.makeevrserg.koleso.feature.participants.domain.usecase.GetWinnerUseCaseImpl

@Composable
fun WheelButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("Wheel!")
    }
}


@Composable
fun Circle(
    data: List<ParticipantWithArc>,
    modifier: Modifier = Modifier
) {
    val size = 128.dp.value
    Canvas(modifier = modifier) {
//        drawCircle(Color.Blue, radius = size / 2)
        data.forEach { entry ->
            drawArc(
                startAngle = entry.arcModel.startAngle,
                sweepAngle = entry.arcModel.sweepAngle,
                color = Color(entry.arcModel.argbColor),
                useCenter = true,
                size = Size(size, size)
            )
        }
    }
}

@Composable
fun Wheel(
    wheelComponent: WheelComponent,
    participantsComponent: ParticipantsComponent
) {
    val model by wheelComponent.configuration.collectAsState()
    val participantsModel by participantsComponent.model.collectAsState()
    Row {
        Column {
            participantsModel.data.forEach { entry ->
                val arc = entry.arcModel
                val participant = entry.participantModel
                Row {
                    Box(Modifier.size(24.dp).background(Color(arc.argbColor)))
                    Text("${participant.desc} -> ${participant.point}")
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("$model")
            when (val model = model) {
                WheelConfiguration.Pending -> {
                    Circle(data = participantsModel.data, modifier = Modifier.size(128.dp))
                    WheelButton(wheelComponent::startWheel)
                }

                is WheelConfiguration.Wheeled -> {
                    val degree by animateFloatAsState(model.degree)
                    Box {
                        Circle(data = participantsModel.data, modifier = Modifier.size(128.dp).rotate(degree))
                        Box(Modifier.size(128.dp).rotate(degree)) {
                            Box(Modifier.size(24.dp).background(Color.Red))
                        }
                    }
                    WheelButton(wheelComponent::startWheel)
                }

                is WheelConfiguration.Wheeling -> {
                    val degree by animateFloatAsState(model.degree)
                    Circle(
                        data = participantsModel.data,
                        modifier = Modifier.size(128.dp).rotate(degree)
                    )
                }
            }
        }
    }
}

fun main() = application {
    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle)
    val participantsComponent = DefaultParticipantsComponent(
        componentContext = rootComponentContext.childContext("ParticipantsComponent")
    )
    val wheelComponent = runOnUiThread {
        DefaultWheelComponent(
            componentContext = rootComponentContext,
            onFinished = {
                println("Winner: ${GetWinnerUseCaseImpl().getWinner(it.degree, participantsComponent.model.value.data)}")
            }
        )
    }

    Window(onCloseRequest = ::exitApplication) {
        MaterialTheme {
            Wheel(wheelComponent, participantsComponent)
        }
    }
}
