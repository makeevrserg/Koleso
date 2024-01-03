package com.makeevrserg.koleso.feature.koleso.participants.domain.usecase

import com.makeevrserg.koleso.feature.koleso.participants.domain.model.ArcModel
import com.makeevrserg.koleso.feature.koleso.participants.domain.model.ParticipantModel
import com.makeevrserg.koleso.feature.koleso.participants.domain.model.ParticipantWithArc
import kotlin.random.Random

interface CreateParticipantWithArcUseCase {
    fun invoke(participants: List<ParticipantModel>): List<ParticipantWithArc>
}
class CreateParticipantWithArcUseCaseImpl : CreateParticipantWithArcUseCase {
    override fun invoke(participants: List<ParticipantModel>): List<ParticipantWithArc> {
        val pointSum = participants.sumOf { it.point }
        var prevPointSum = 0
        return participants
            .filter { it.point >= 0 }
            .map { participant ->
                val sweepAngle = participant.point.toFloat() / pointSum * 360
                val startAngle = prevPointSum.toFloat() / pointSum * 360
                prevPointSum += participant.point

                ParticipantWithArc(
                    participantModel = participant,
                    arcModel = ArcModel(
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        argbColor = Random.nextInt()
                    )
                )
            }
    }
}
