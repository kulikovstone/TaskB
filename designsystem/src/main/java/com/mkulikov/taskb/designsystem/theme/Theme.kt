package com.mkulikov.taskb.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun TaskBTheme(
    content: @Composable () -> Unit
) {
    val isDark = isSystemInDarkTheme()
    val colorScheme = if (isDark) DarkColorScheme else LightColorScheme
    val customColors = if (isDark) getCustomColorsDark() else getCustomColorsLight()

    CompositionLocalProvider(
        LocalCustomColor provides customColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content,
        )
    }
}

object PhoenixTheme {

    val customColor: CustomColor
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomColor.current
}