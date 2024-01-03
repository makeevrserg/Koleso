package com.makeevrserg.koleso.feature.koleso.wheel.ui.component

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun WheelButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("Wheel!")
    }
}
