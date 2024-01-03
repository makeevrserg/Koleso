package com.makeevrserg.koleso.feature.koleso.participants.domain.usecase

import com.makeevrserg.koleso.feature.koleso.participants.domain.model.ArcModel
import com.makeevrserg.koleso.feature.koleso.participants.domain.model.ParticipantWithArc

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
                    false
                }

                startAngle <= 360f && endAngle >= 360f -> {
                    true
                }

                startAngle >= 360f && endAngle >= 360f -> {
                    false
                }

                else -> error("Not implemented")
            }
        }
    }
}
