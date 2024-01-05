package com.makeevrserg.koleso.feature.koleso.root.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.makeevrserg.koleso.feature.koleso.dialog.presentation.DialogComponent
import com.makeevrserg.koleso.feature.koleso.participants.presentation.DefaultParticipantsComponent
import com.makeevrserg.koleso.feature.koleso.participants.presentation.ParticipantsComponent
import com.makeevrserg.koleso.feature.koleso.wheel.presentation.DefaultWheelComponent
import com.makeevrserg.koleso.feature.koleso.wheel.presentation.WheelComponent
import com.makeevrserg.koleso.feature.koleso.winner.presentation.DefaultWinnerComponent
import com.makeevrserg.koleso.feature.koleso.winner.presentation.WinnerComponent
import com.makeevrserg.koleso.service.db.api.ParticipantsApi

class DefaultRootKolesoComponent(
    componentContext: ComponentContext,
    override val dialogComponent: DialogComponent,
    private val participantsApi: ParticipantsApi
) : RootKolesoComponent, ComponentContext by componentContext {
    override val participantsComponent: ParticipantsComponent = DefaultParticipantsComponent(
        componentContext = childContext("ParticipantsComponent"),
        participantsApi = participantsApi
    )
    override val winnerComponent: WinnerComponent = DefaultWinnerComponent(
        componentContext = childContext("winnerComponent")
    )
    override val wheelComponent: WheelComponent = DefaultWheelComponent(
        componentContext = childContext("wheelComponent"),
        onFinished = {
            winnerComponent.onWheelRotated(it.degree, participantsComponent.model.value.data)
        },
        onStarted = {
            winnerComponent.reset()
        },
        onRotating = {
            winnerComponent.onWheelRotating(it.degree, participantsComponent.model.value.data)
        }
    )
}
