package com.makeevrserg.koleso.feature.koleso

import com.makeevrserg.koleso.feature.koleso.domain.model.WheelConfiguration
import kotlinx.coroutines.flow.StateFlow

interface WheelComponent {
    val configuration: StateFlow<WheelConfiguration>

    fun startWheel()

    fun stopWheel()

    fun reset()
}
