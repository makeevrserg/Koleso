package com.makeevrserg.koleso.feature.koleso.wheel.domain.usecase

import com.makeevrserg.koleso.feature.koleso.wheel.domain.model.WheelConfiguration
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.math.cosh
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

interface GetWheelConfigurationFlowUseCase {
    fun invoke(getModel: () -> WheelConfiguration): Flow<WheelConfiguration>
}

class GetWheelConfigurationFlowUseCaseImpl : GetWheelConfigurationFlowUseCase {
    private val seed = Random.nextInt()
    private val random = Random(seed)

    private val rotationGain = (random.nextInt(40, 45))
    private val initialPower = random.nextDouble(0.7, 1.4).toFloat()

    /**
     * Returns current rotation amount by it's [power]
     *
     * The more [power] - more rotation gain
     */
    private fun getRotationByPower(power: Float) = (cosh(power) - 1) * rotationGain

    /**
     * Calculates next power gain
     */
    private fun getNextPower(power: Float): Float {
//        return power - 0.01f
        return when {
            power > 0.3f -> power - 0.001f
            power > 0.2f -> power - 0.0008f
            else -> power - 0.0002f
        }
    }

    /**
     * If previous configuration contains degree - we will take it into the next
     */
    private fun getPrevDegree(configuration: WheelConfiguration): Float {
        return (configuration as? WheelConfiguration.Wheeled)?.degree
            ?: (configuration as? WheelConfiguration.Wheeling)?.degree
            ?: 0f
    }

    override fun invoke(getModel: () -> WheelConfiguration) = flow {
        WheelConfiguration.Wheeling(
            degree = getPrevDegree(getModel.invoke()),
            power = initialPower
        ).run { emit(this) }

        do {
            val localModel = getModel.invoke() as? WheelConfiguration.Wheeling ?: return@flow
            val nextModel = localModel.copy(
                power = getNextPower(localModel.power),
                degree = localModel.degree + getRotationByPower(localModel.power),
            )
            emit(nextModel)
            delay(1.milliseconds)
        } while (localModel.power > 0f)

        val finishModel = (getModel.invoke() as? WheelConfiguration.Wheeling)
            ?.degree
            ?.let(WheelConfiguration::Wheeled)
            ?: getModel.invoke()
        emit(finishModel)
    }
}
