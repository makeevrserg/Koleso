package com.makeevrserg.koleso.feature.koleso.root.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makeevrserg.koleso.feature.koleso.participants.ui.ParticipantsContent
import com.makeevrserg.koleso.feature.koleso.participants.ui.component.AddParticipantFloatingActionButton
import com.makeevrserg.koleso.feature.koleso.root.presentation.DefaultRootKolesoComponent
import com.makeevrserg.koleso.feature.koleso.wheel.ui.WheelContent
import com.makeevrserg.koleso.feature.koleso.wheel.ui.component.WheelExtendedFloatingActionButton
import com.makeevrserg.koleso.feature.koleso.winner.ui.WinnerContent

@Composable
fun KolesoScreen(
    rootKolesoComponent: DefaultRootKolesoComponent
) {
    Scaffold(
        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.animateContentSize(),
                horizontalAlignment = Alignment.End
            ) {
                AddParticipantFloatingActionButton(
                    wheelComponent = rootKolesoComponent.wheelComponent,
                    onClick = {
                        rootKolesoComponent.dialogComponent.openEditParticipant()
                    }
                )
                WheelExtendedFloatingActionButton(rootKolesoComponent.wheelComponent)
            }
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize().animateContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            WheelContent(rootKolesoComponent.wheelComponent, rootKolesoComponent.participantsComponent)
            WinnerContent(rootKolesoComponent.winnerComponent)
            ParticipantsContent(rootKolesoComponent.participantsComponent, rootKolesoComponent.dialogComponent)
        }
    }
}
