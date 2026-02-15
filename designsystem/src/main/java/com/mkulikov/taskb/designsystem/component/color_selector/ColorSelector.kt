package com.mkulikov.taskb.core.designsystem.component.color_selector

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.core.designsystem.theme.grey800
import com.mkulikov.taskb.designsystem.util.PreviewContainer

@Composable
fun ColorSelector(
    name: String,
    color: Color,
    selected: Boolean,
    onClick: (Boolean) -> Unit,
) {
    FilterChip(
        onClick = { onClick(!selected) },
        label = { Text(text = name) },
        selected = selected,
        leadingIcon = {
            if (selected) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(color)
                )
            }
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = color,
            selectedLabelColor = grey800,
            selectedLeadingIconColor = grey800,
        )
    )
}

@Composable
@PreviewLightDark
private fun ColorSelectorPreview(
    @PreviewParameter(ColorSelectorDataProvider::class)
    data: ColorSelectorDataProvider.Data,
) = PreviewContainer(
    modifier = Modifier.padding(horizontal = 8.dp)
) {
    ColorSelector(
        name = data.name,
        color = data.color,
        selected = data.selected,
        onClick = {},
    )
}