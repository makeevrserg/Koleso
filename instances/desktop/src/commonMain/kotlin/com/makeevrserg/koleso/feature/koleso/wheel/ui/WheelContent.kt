package com.makeevrserg.koleso.feature.koleso.wheel.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.makeevrserg.koleso.feature.koleso.participants.presentation.ParticipantsComponent
import com.makeevrserg.koleso.feature.koleso.wheel.domain.model.WheelConfiguration
import com.makeevrserg.koleso.feature.koleso.wheel.presentation.WheelComponent
import com.makeevrserg.koleso.feature.koleso.wheel.ui.component.CircleWithArrow
import com.makeevrserg.koleso.feature.koleso.wheel.ui.component.WheelButton

@Composable
fun WheelContent(wheelComponent: WheelComponent, participantsComponent: ParticipantsComponent) {
    val wheelConfiguration by wheelComponent.configuration.collectAsState()
    val participantsModel by participantsComponent.model.collectAsState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.animateContentSize()
    ) {
        when (wheelConfiguration) {
            is WheelConfiguration.Pending -> {
                CircleWithArrow(
                    wheelConfiguration = wheelConfiguration,
                    participantsModel = participantsModel
                )
            }

            is WheelConfiguration.Wheeled -> {
                CircleWithArrow(
                    wheelConfiguration = wheelConfiguration,
                    participantsModel = participantsModel
                )
            }

            is WheelConfiguration.Wheeling -> {
                CircleWithArrow(
                    wheelConfiguration = wheelConfiguration,
                    participantsModel = participantsModel
                )
            }
        }
    }
}
