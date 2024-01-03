package com.makeevrserg.koleso.feature.koleso.wheel.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.makeevrserg.koleso.feature.koleso.wheel.domain.model.WheelConfiguration
import com.makeevrserg.koleso.feature.koleso.wheel.presentation.WheelComponent
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun WheelExtendedFloatingActionButton(wheelComponent: WheelComponent) {
    val model by wheelComponent.configuration
        .distinctUntilChanged { old, new -> old::class == new::class }
        .collectAsState(WheelConfiguration.Pending(0f))

    val icon = when (model) {
        is WheelConfiguration.Wheeled,
        is WheelConfiguration.Pending -> Icons.Filled.Refresh

        is WheelConfiguration.Wheeling -> Icons.Filled.Close
    }
    val text = when (model) {
        is WheelConfiguration.Wheeled,
        is WheelConfiguration.Pending -> "Wheel this wheel!"

        is WheelConfiguration.Wheeling -> "Stop!"
    }
    ExtendedFloatingActionButton(
        modifier = Modifier.animateContentSize(),
        onClick = {
            when (model) {
                is WheelConfiguration.Wheeled, is WheelConfiguration.Pending -> wheelComponent.startWheel()
                is WheelConfiguration.Wheeling -> wheelComponent.stopWheel()
            }
        },
        text = {
            AnimatedContent(text) { text ->
                Text(text)
            }
        },
        icon = {
            Crossfade(icon) { icon ->
                Icon(imageVector = icon, contentDescription = null)
            }
        }
    )
}