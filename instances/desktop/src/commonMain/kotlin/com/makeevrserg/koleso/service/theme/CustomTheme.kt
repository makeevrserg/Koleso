package com.makeevrserg.koleso.service.theme

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
            primary = Color(0xFF88ceff),
            onPrimary = Color(0xFFfabd00),
            primaryContainer = Color(0xFF004c6d),
            onPrimaryContainer = Color(0xFFc8e6ff),
            secondary = Color(0xFFfabd00),
            onSecondary = Color(0xFF3f2e00),
            secondaryContainer = Color(0xFF5b4300),
            onSecondaryContainer = Color(0xFFffdf9d),
            tertiary = Color(0xFFa3c9ff),
            onTertiary = Color(0xFF00315c),
            tertiaryContainer = Color(0xFF004882),
            onTertiaryContainer = Color(0xFFd3e3ff),
            error = Color(0xFFffb4ab),
            onError = Color(0xFF690005),
            errorContainer = Color(0xFF93000a),
            onErrorContainer = Color(0xFFffdad6),
            background = Color(0xFF001f2a),
            onBackground = Color(0xFFbfe9ff),
            surface = Color(0xFF001f2a),
            onSurface = Color(0xFFbfe9ff),
            outline = Color(0xFF8b9198),
            surfaceVariant = Color(0xFF41484d),
            onSurfaceVariant = Color(0xFFc1c7ce),

            inversePrimary = Color(0xFF88ceff).inverse(),
            surfaceTint = Color(0xFF001f2a).inverse(),
            inverseSurface = Color(0xFF001f2a).inverse(),
            inverseOnSurface = Color(0xFFbfe9ff).inverse(),
            outlineVariant = Color(0xFF8b9198).inverse(),
            scrim = Color(0xFF00315c)
        ),
        content = content
    )
}

@Composable
fun CustomTheme(content: @Composable () -> Unit) {
    CustomMaterialTheme { CustomMaterial3Theme { content.invoke() } }
}
