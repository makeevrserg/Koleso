package com.makeevrserg.koleso.feature.koleso

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.koleso.service.core.CoroutineFeature
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class DefaultWheelComponent(
    componentContext: ComponentContext
) : WheelComponent,
    ComponentContext by componentContext {
    override val model = MutableStateFlow<WheelComponent.Model>(WheelComponent.Model.Pending)
    private val coroutineFeature = instanceKeeper.getOrCreate {
        CoroutineFeature.Default()
    }

    private var job: Job? = null

    override fun startWheel() {
        coroutineFeature.launch {
            job?.cancelAndJoin()
            job = launch {
                val prevDegree = (model.value as? WheelComponent.Model.Wheeled)?.degree
                    ?: (model.value as? WheelComponent.Model.Wheeling)?.degree
                    ?: 0f
                model.value = WheelComponent.Model.Wheeling(degree = prevDegree, power = 1f)
                do {
                    val localModel = model.value as? WheelComponent.Model.Wheeling ?: return@launch
                    model.value = localModel.copy(
                        power = localModel.power - 0.0005f,
                        degree = localModel.degree + localModel.power * 30,
                    )
                    delay(1.milliseconds)
                } while (localModel.power > 0f)
                model.update {
                    (it as? WheelComponent.Model.Wheeling)?.let {
                        WheelComponent.Model.Wheeled(it.degree)
                    } ?: it
                }
            }
        }
    }

    override fun stopWheel() {
        model.value = when (val currentModel = model.value) {
            WheelComponent.Model.Pending -> currentModel
            is WheelComponent.Model.Wheeled -> currentModel
            is WheelComponent.Model.Wheeling -> WheelComponent.Model.Wheeled(currentModel.degree)
        }
    }

    override fun reset() {
        model.value = WheelComponent.Model.Pending
    }
}
