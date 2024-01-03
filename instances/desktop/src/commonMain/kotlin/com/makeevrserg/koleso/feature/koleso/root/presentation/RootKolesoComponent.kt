package com.makeevrserg.koleso.feature.koleso.root.presentation

import com.makeevrserg.koleso.feature.koleso.participants.presentation.ParticipantsComponent
import com.makeevrserg.koleso.feature.koleso.wheel.presentation.WheelComponent

interface RootKolesoComponent {
    val participantsComponent: ParticipantsComponent
    val wheelComponent: WheelComponent
}
