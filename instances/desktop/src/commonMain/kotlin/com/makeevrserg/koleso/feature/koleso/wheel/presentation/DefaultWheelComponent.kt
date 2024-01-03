package com.makeevrserg.koleso.feature.koleso.wheel.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.koleso.feature.koleso.wheel.domain.model.WheelConfiguration
import com.makeevrserg.koleso.feature.koleso.wheel.domain.usecase.GetWheelConfigurationFlowUseCase
import com.makeevrserg.koleso.feature.koleso.wheel.domain.usecase.GetWheelConfigurationFlowUseCaseImpl
import com.makeevrserg.koleso.service.core.CoroutineFeature
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DefaultWheelComponent(
    componentContext: ComponentContext,
    private val onFinished: (WheelConfiguration.Wheeled) -> Unit,
    private val onStarted: () -> Unit,
    private val onRotating: (WheelConfiguration.Wheeling) -> Unit
) : WheelComponent,
    ComponentContext by componentContext {
    private val getWheelFlowUseCase: GetWheelConfigurationFlowUseCase
        get() = GetWheelConfigurationFlowUseCaseImpl()
    override val configuration = MutableStateFlow<WheelConfiguration>(WheelConfiguration.Pending(0f))
    private val coroutineFeature = instanceKeeper.getOrCreate {
        CoroutineFeature.Default()
    }

    private var job: Job? = null

    override fun startWheel() {
        onStarted.invoke()
        coroutineFeature.launch {
            job?.cancelAndJoin()
            job = getWheelFlowUseCase.invoke { configuration.value }
                .onEach {
                    configuration.value = it
                    (it as? WheelConfiguration.Wheeling)?.let(onRotating)
                }
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
            is WheelConfiguration.Pending -> currentModel
            is WheelConfiguration.Wheeled -> currentModel
            is WheelConfiguration.Wheeling -> WheelConfiguration.Wheeled(currentModel.degree)
        }
    }

    override fun reset() {
        onStarted.invoke()
        configuration.value = WheelConfiguration.Pending(0f)
    }
}
