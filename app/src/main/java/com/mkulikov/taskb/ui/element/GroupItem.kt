package com.mkulikov.taskb.ui.element

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.theme.PhoenixTheme

@Composable
fun GroupItem(
    name: String,
    color: Color?,
    count: Int,
    isSelected: Boolean,
    select: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(getGroupBackgroundColor(isSelected))
            .clickable(onClick = select),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(color ?: Color.Blue),
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                modifier = Modifier.weight(1f),
                text = name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            if (count > 0) {
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = "$count",
                )
            }
        }
    }
}

@Composable
fun getGroupBackgroundColor(isSelected: Boolean) = if (isSelected) {
    PhoenixTheme.customColor.selectedGroupBackground
} else {
    Color.Transparent
}