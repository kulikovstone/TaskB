package com.mkulikov.taskb.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class CustomColor (
    val selectedGroupBackground: Color,
    val groupListBackground: Color,
    val listTitleColor: Color,
)

fun getCustomColorsLight() = CustomColor(
    selectedGroupBackground = blue50,
    groupListBackground = grey50,
    listTitleColor = grey500,
)

fun getCustomColorsDark() = CustomColor(
    selectedGroupBackground = blue50,
    groupListBackground = grey100,
    listTitleColor = grey500,
)

internal val LocalCustomColor = staticCompositionLocalOf<CustomColor> {
    error("LocalCustomColors not present")
}