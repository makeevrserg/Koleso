package com.makeevrserg.koleso.service.core.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private fun Color.inverse() = Color(0xFFFFFFFF.toULong().minus(value))

@Composable
private fun CustomMaterialTheme(content: @Composable () -> Unit) {
    androidx.compose.material.MaterialTheme(
        colors = darkColors(
            primary = Color(0xFF252B30),
            primaryVariant = Color(0xFF161A1D),
            background = Color(0xFF161A1D),
            secondary = Color(0xFFFFC100),
            secondaryVariant = Color(0xFF1B76CA),
            onPrimary = Color(0xFFFFFFFF),
            onSecondary = Color(0xFF697C8A),
            surface = Color(0xFFFFFFFF),
            onSurface = Color(0xFF000000),
        ),
        content = content
    )
}

@Composable
private fun CustomMaterial3Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFFe9c334),
            onPrimary = Color(0xFF3b2f00),
            primaryContainer = Color(0xFF564500),
            onPrimaryContainer = Color(0xFFffe17c),
            secondary = Color(0xFFafc6ff),
            onSecondary = Color(0xFF002d6d),
            secondaryContainer = Color(0xFF004299),
            onSecondaryContainer = Color(0xFFd9e2ff),
            tertiary = Color(0xFF4cdadd),
            onTertiary = Color(0xFF003738),
            tertiaryContainer = Color(0xFF004f51),
            onTertiaryContainer = Color(0xFF001c39),
            error = Color(0xFFffb4ab),
            onError = Color(0xFF690005),
            errorContainer = Color(0xFF93000a),
            onErrorContainer = Color(0xFFffdad6),
            background = Color(0xFF001f25),
            onBackground = Color(0xFFa6eeff),
            surface = Color(0xFF001f25),
            onSurface = Color(0xFFa6eeff),
            outline = Color(0xFF979080),
            surfaceVariant = Color(0xFF4b4639),
            onSurfaceVariant = Color(0xFFcec6b4),

            inversePrimary = Color(0xFF006590).inverse(),
            surfaceTint = Color(0xFFfafcff).inverse(),
            inverseSurface = Color(0xFFfafcff).inverse(),
            inverseOnSurface = Color(0xFF001f2a).inverse(),
            outlineVariant = Color(0xFF71787e).inverse(),
            scrim = Color(0xFF785a00)
        ),
        content = content
    )
}

@Composable
fun CustomTheme(content: @Composable () -> Unit) {
    CustomMaterialTheme { CustomMaterial3Theme { content.invoke() } }
}
