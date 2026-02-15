package com.mkulikov.taskb.core.designsystem.component.color_selector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

internal class ColorSelectorDataProvider : PreviewParameterProvider<ColorSelectorDataProvider.Data> {

    override val values: Sequence<Data> = sequenceOf(
        Data(
            name = "Светло-зеленый",
            color = Color(0xFFDCEDC8),
            selected = false,
        ),
        Data(
            name = "Светло-зеленый",
            color = Color(0xFFDCEDC8),
            selected = true,
        )

    )

    data class Data(
        val name: String,
        val color: Color,
        val selected: Boolean,
    )
}