package com.djinc.eduspeech.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
        primary = AppPrimary,
        primaryVariant = AppSecondary,
        secondary = BluePrimary
)

private val LightColorPalette = lightColors(
        primary = AppPrimary,
        primaryVariant = AppSecondary,
        secondary = BluePrimary
)

@Composable
fun EdumotiveTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
//        DarkColorPalette
        LightColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}
