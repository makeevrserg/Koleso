package com.makeevrserg.koleso.feature.participants.domain.usecase

import com.makeevrserg.koleso.feature.participants.domain.model.ParticipantWithArc
import kotlin.math.sin

interface GetWinnerUseCase {
    fun getWinner(degree: Float, data: List<ParticipantWithArc>): ParticipantWithArc
}

class GetWinnerUseCaseImpl : GetWinnerUseCase {
    override fun getWinner(degree: Float, data: List<ParticipantWithArc>): ParticipantWithArc {
        val degree = (degree) % 360
        return data.first {
            val startAngle = (it.arcModel.startAngle + degree) % 360
            val endAngle = (it.arcModel.sweepAngle + it.arcModel.sweepAngle + degree * 2) % 360
            0.0 in sin(startAngle)..sin(endAngle)
                    || 0.0 in sin(endAngle)..sin(startAngle)
        }
    }
}