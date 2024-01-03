package com.makeevrserg.koleso.feature.koleso.root.presentation

import com.makeevrserg.koleso.feature.koleso.dialog.presentation.DialogComponent
import com.makeevrserg.koleso.feature.koleso.participants.presentation.ParticipantsComponent
import com.makeevrserg.koleso.feature.koleso.wheel.presentation.WheelComponent
import com.makeevrserg.koleso.feature.koleso.winner.presentation.WinnerComponent

interface RootKolesoComponent {
    val participantsComponent: ParticipantsComponent
    val wheelComponent: WheelComponent
    val winnerComponent: WinnerComponent
    val dialogComponent: DialogComponent
}
