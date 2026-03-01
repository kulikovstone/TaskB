package com.mkulikov.taskb.designsystem.color

import androidx.compose.ui.graphics.Color
import com.mkulikov.taskb.designsystem.theme.blue200
import com.mkulikov.taskb.designsystem.theme.green200
import com.mkulikov.taskb.designsystem.theme.red200

enum class StatisticsColor(
    val color: Color,
) {
    Active(green200),
    Completed(blue200),
    Deleted(red200);
}
