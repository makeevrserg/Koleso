package com.makeevrserg.koleso.feature.koleso.winner.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.koleso.feature.koleso.participants.domain.model.ParticipantWithArc
import com.makeevrserg.koleso.feature.koleso.participants.domain.usecase.GetWinnerUseCaseImpl
import com.makeevrserg.koleso.service.core.CoroutineFeature
import com.makeevrserg.koleso.service.core.throttleFirst
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultWinnerComponent(
    componentContext: ComponentContext
) : WinnerComponent, ComponentContext by componentContext {
    private val getWinnerUseCase = GetWinnerUseCaseImpl()
    private val coroutineFeature = instanceKeeper.getOrCreate { CoroutineFeature.Default() }
    override val model = MutableStateFlow<WinnerComponent.Model>(WinnerComponent.Model.Pending)

    override fun onWheelRotated(degree: Float, data: List<ParticipantWithArc>) {
        if (data.isEmpty()) return reset()
        val winner = getWinnerUseCase.invoke(degree, data)
        model.value = WinnerComponent.Model.Winner(winner)
    }

    private class TempModel(
        val degree: Float,
        val data: List<ParticipantWithArc>
    )

    private val rotatingThrottle = throttleFirst<TempModel>(
        skipMs = 100L,
        coroutineScope = coroutineFeature,
        destinationFunction = { tempModel ->
            val winner = getWinnerUseCase.invoke(tempModel.degree, tempModel.data)
            model.value = WinnerComponent.Model.Rotating(winner)
        }
    )

    override fun onWheelRotating(degree: Float, data: List<ParticipantWithArc>) {
        if (data.isEmpty()) return reset()
        rotatingThrottle.invoke(TempModel(degree, data))
    }

    override fun reset() {
        model.value = WinnerComponent.Model.Pending
    }
}
