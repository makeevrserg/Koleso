package com.makeevrserg.koleso.feature.participants.domain.usecase

import com.makeevrserg.koleso.feature.participants.domain.model.ArcModel
import com.makeevrserg.koleso.feature.participants.domain.model.ParticipantWithArc
import kotlin.math.sin

interface GetWinnerUseCase {
    fun getWinner(degree: Float, data: List<ParticipantWithArc>): ParticipantWithArc
}

class GetWinnerUseCaseImpl : GetWinnerUseCase {
    private val ArcModel.endAngle: Float
        get() = startAngle + sweepAngle


    override fun getWinner(degree: Float, data: List<ParticipantWithArc>): ParticipantWithArc {
        val degree = (degree) % 360
        return data.first {
            val arc = it.arcModel
            val startAngle = arc.startAngle + degree
            val endAngle = arc.endAngle + degree
            when {
                startAngle <= 360 && endAngle <= 360f -> {
                    0f in startAngle..endAngle
                }

                startAngle <= 360f && endAngle >= 360f -> {
                    0f in startAngle..360f || 0f in 0f..(endAngle % 360f)
                }

                startAngle >= 360f && endAngle >= 360f -> {
                    0f in (startAngle % 360f)..(endAngle % 360f)
                }

                else -> error("Not implemented")
            }
        }
    }
}