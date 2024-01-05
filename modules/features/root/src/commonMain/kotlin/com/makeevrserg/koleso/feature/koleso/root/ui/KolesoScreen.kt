package com.makeevrserg.koleso.feature.koleso.root.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makeevrserg.koleso.feature.koleso.participants.ui.ParticipantsContent
import com.makeevrserg.koleso.feature.koleso.participants.ui.component.AddParticipantFloatingActionButton
import com.makeevrserg.koleso.feature.koleso.root.presentation.DefaultRootKolesoComponent
import com.makeevrserg.koleso.feature.koleso.wheel.ui.EmptyWheelContent
import com.makeevrserg.koleso.feature.koleso.wheel.ui.NotEnoughForWheelContent
import com.makeevrserg.koleso.feature.koleso.wheel.ui.WheelContent
import com.makeevrserg.koleso.feature.koleso.wheel.ui.component.WheelExtendedFloatingActionButton
import com.makeevrserg.koleso.feature.koleso.winner.ui.WinnerContent

@Composable
fun KolesoScreen(
    rootKolesoComponent: DefaultRootKolesoComponent
) {
    val participantsModel by rootKolesoComponent.participantsComponent.model.collectAsState()
    Scaffold(
        floatingActionButton = {
            AnimatedVisibility(participantsModel.hasEnoughParticipants, enter = fadeIn(), exit = fadeOut()) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Bottom),
                    modifier = Modifier.fillMaxSize().animateContentSize(),
                    horizontalAlignment = Alignment.End,
                ) {
                    AddParticipantFloatingActionButton(
                        wheelComponent = rootKolesoComponent.wheelComponent,
                        onClick = {
                            rootKolesoComponent.dialogComponent.openEditParticipant()
                        }
                    )
                    WheelExtendedFloatingActionButton(wheelComponent = rootKolesoComponent.wheelComponent)
                }
            }
        },
    ) {
        Crossfade(participantsModel) { participantsModel ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                when {
                    !participantsModel.hasAnyParticipant -> {
                        EmptyWheelContent(rootKolesoComponent.dialogComponent::openEditParticipant)
                    }

                    !participantsModel.hasEnoughParticipants -> {
                        Column(
                            modifier = Modifier.fillMaxSize().animateContentSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            NotEnoughForWheelContent(rootKolesoComponent.dialogComponent::openEditParticipant)
                            ParticipantsContent(
                                rootKolesoComponent.participantsComponent,
                                rootKolesoComponent.dialogComponent
                            )
                        }
                    }

                    else -> {
                        Column(
                            modifier = Modifier.fillMaxSize().animateContentSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            WheelContent(rootKolesoComponent.wheelComponent, rootKolesoComponent.participantsComponent)
                            WinnerContent(rootKolesoComponent.winnerComponent)
                            ParticipantsContent(
                                rootKolesoComponent.participantsComponent,
                                rootKolesoComponent.dialogComponent
                            )
                        }
                    }
                }
            }
        }
    }
}
