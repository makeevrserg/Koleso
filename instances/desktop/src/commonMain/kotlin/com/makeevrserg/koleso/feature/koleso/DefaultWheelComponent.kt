package com.makeevrserg.koleso.feature.koleso

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultWheelComponent(
    componentContext: ComponentContext
) : WheelComponent,
    ComponentContext by componentContext {
    override val model = MutableStateFlow<WheelComponent.Model>(WheelComponent.Model.Pending)
    private val coroutineFeature = instanceKeeper.getOrCreate {
        CoroutineFeature
    }

    private var job: Job? = null

    override fun startWheel() {
        TODO("Not yet implemented")
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