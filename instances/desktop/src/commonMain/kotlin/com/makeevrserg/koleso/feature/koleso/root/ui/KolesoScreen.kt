package com.makeevrserg.koleso.feature.koleso.root.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.makeevrserg.koleso.feature.koleso.participants.ui.ParticipantsContent
import com.makeevrserg.koleso.feature.koleso.root.presentation.DefaultRootKolesoComponent
import com.makeevrserg.koleso.feature.koleso.wheel.ui.WheelContent

@Composable
fun KolesoScreen(
    rootKolesoComponent: DefaultRootKolesoComponent
) {
    Row {
        ParticipantsContent(rootKolesoComponent.participantsComponent)
        WheelContent(rootKolesoComponent.wheelComponent, rootKolesoComponent.participantsComponent)
    }
}
