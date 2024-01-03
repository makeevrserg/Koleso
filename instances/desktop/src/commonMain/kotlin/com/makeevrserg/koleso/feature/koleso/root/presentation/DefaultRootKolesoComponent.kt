package com.makeevrserg.koleso.feature.koleso.root.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.makeevrserg.koleso.feature.koleso.participants.domain.usecase.GetWinnerUseCaseImpl
import com.makeevrserg.koleso.feature.koleso.participants.presentation.DefaultParticipantsComponent
import com.makeevrserg.koleso.feature.koleso.participants.presentation.ParticipantsComponent
import com.makeevrserg.koleso.feature.koleso.wheel.presentation.DefaultWheelComponent
import com.makeevrserg.koleso.feature.koleso.wheel.presentation.WheelComponent

class DefaultRootKolesoComponent(
    componentContext: ComponentContext
) : RootKolesoComponent, ComponentContext by componentContext {
    override val participantsComponent: ParticipantsComponent = DefaultParticipantsComponent(
        componentContext = childContext("ParticipantsComponent")
    )
    override val wheelComponent: WheelComponent = DefaultWheelComponent(
        componentContext = childContext("wheelComponent"),
        onFinished = {
            println(
                "Winner: ${
                GetWinnerUseCaseImpl().getWinner(
                    it.degree,
                    participantsComponent.model.value.data
                )
                }"
            )
        }
    )
}
