package com.mkulikov.taskb.core.designsystem.component.group_selector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mkulikov.taskb.core.designsystem.theme.purple100

internal class GroupSelectorDataProvider : PreviewParameterProvider<GroupSelectorDataProvider.Data> {

    override val values: Sequence<Data> = sequenceOf(
        Data(
            name = "Обучение",
            color = purple100,
            selected = false,
        ),
        Data(
            name = "Обучение",
            color = purple100,
            selected = true,
        )

    )

    data class Data(
        val name: String,
        val color: Color,
        val selected: Boolean,
    )
}