package com.makeevrserg.koleso.feature.participants.domain.usecase

import com.makeevrserg.koleso.feature.koleso.participants.domain.model.ParticipantModel
import com.makeevrserg.koleso.feature.koleso.participants.domain.usecase.CreateParticipantWithArcUseCaseImpl
import com.makeevrserg.koleso.feature.koleso.participants.domain.usecase.GetWinnerUseCaseImpl
import kotlin.test.Test
import kotlin.test.assertEquals

class GetWinnerUseCaseTest {

    @Test
    fun sinTest() {
        fun radians(degree: Float) = (degree * 0.017453292519943295)
        println(kotlin.math.cos(0f.let(::radians)))
        println(kotlin.math.cos(90f.let(::radians)))
        println(kotlin.math.cos(180f.let(::radians)))
        println(kotlin.math.cos(270f.let(::radians)))
        println(kotlin.math.cos(360f.let(::radians)))
    }

    @Test
    fun testDegree() {
        val data = CreateParticipantWithArcUseCaseImpl().invoke(
            participants = listOf(
                ParticipantModel("Part 1", 1),
                ParticipantModel("Part 2", 1),
                ParticipantModel("Part 3", 1),
                ParticipantModel("Part 4", 1),
            )
        )
        println(data)
        (0..4).forEach {
            val i = it % 4
            val degree = 4f + 90 * it
            GetWinnerUseCaseImpl().getWinner(degree, data).let { winner ->
                println("Degree: $degree, winner: $winner")
                assertEquals(data[4 - i - 1], winner)
            }
        }
    }

    @Test
    fun testDegree2() {
        val data = CreateParticipantWithArcUseCaseImpl().invoke(
            participants = listOf(
                ParticipantModel("Part 0", 6),
                ParticipantModel("Part 1", 0),
                ParticipantModel("Part 2", 5),
            )
        )
        GetWinnerUseCaseImpl().getWinner(3382f, data).let { winner ->
            println("Degree: 645.24915f, winner: $winner")
            assertEquals(data[2], winner)
        }
    }
}
