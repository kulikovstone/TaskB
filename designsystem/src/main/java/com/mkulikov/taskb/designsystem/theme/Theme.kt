package com.mkulikov.taskb.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.mkulikov.taskb.core.designsystem.theme.CustomColor
import com.mkulikov.taskb.core.designsystem.theme.DarkColorScheme
import com.mkulikov.taskb.core.designsystem.theme.LightColorScheme
import com.mkulikov.taskb.core.designsystem.theme.LocalCustomColor
import com.mkulikov.taskb.core.designsystem.theme.getCustomColorsDark
import com.mkulikov.taskb.core.designsystem.theme.getCustomColorsLight

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