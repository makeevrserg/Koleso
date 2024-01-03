package com.makeevrserg.koleso.feature.koleso.winner.presentation

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.koleso.feature.koleso.participants.domain.model.ParticipantWithArc
import com.makeevrserg.koleso.feature.koleso.participants.domain.usecase.GetWinnerUseCaseImpl
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultWinnerComponent(
    componentContext: ComponentContext
) : WinnerComponent, ComponentContext by componentContext {
    private val getWinnerUseCase = GetWinnerUseCaseImpl()
    override val model = MutableStateFlow<WinnerComponent.Model>(WinnerComponent.Model.Pending)

    override fun onWheelRotated(degree: Float, data: List<ParticipantWithArc>) {
        val winner = getWinnerUseCase.invoke(degree, data)
        model.value = WinnerComponent.Model.Winner(winner)
    }

    override fun reset() {
        model.value = WinnerComponent.Model.Pending
    }
}