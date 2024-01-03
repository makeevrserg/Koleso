package com.makeevrserg.koleso.feature.koleso

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.koleso.feature.koleso.domain.model.WheelConfiguration
import com.makeevrserg.koleso.feature.koleso.domain.usecase.GetWheelConfigurationFlowUseCase
import com.makeevrserg.koleso.feature.koleso.domain.usecase.GetWheelConfigurationFlowUseCaseImpl
import com.makeevrserg.koleso.service.core.CoroutineFeature
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DefaultWheelComponent(
    componentContext: ComponentContext,
    private val onFinished: (WheelConfiguration.Wheeled) -> Unit
) : WheelComponent,
    ComponentContext by componentContext {
    private val getWheelFlowUseCase: GetWheelConfigurationFlowUseCase
        get() = GetWheelConfigurationFlowUseCaseImpl()
    override val configuration = MutableStateFlow<WheelConfiguration>(WheelConfiguration.Pending)
    private val coroutineFeature = instanceKeeper.getOrCreate {
        CoroutineFeature.Default()
    }

    private var job: Job? = null

    override fun startWheel() {
        coroutineFeature.launch {
            job?.cancelAndJoin()
            job = getWheelFlowUseCase.invoke { configuration.value }
                .onEach { configuration.value = it }
                .launchIn(coroutineFeature)

            job?.invokeOnCompletion {
                job?.cancel()
                job = null
                (configuration.value as? WheelConfiguration.Wheeled)?.run(onFinished)
            }
        }
    }

    override fun stopWheel() {
        configuration.value = when (val currentModel = configuration.value) {
            WheelConfiguration.Pending -> currentModel
            is WheelConfiguration.Wheeled -> currentModel
            is WheelConfiguration.Wheeling -> WheelConfiguration.Wheeled(currentModel.degree)
        }
    }

    override fun reset() {
        configuration.value = WheelConfiguration.Pending
    }
}
