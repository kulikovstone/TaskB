package com.mkulikov.taskb.group.screen.edit_group.part

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.designsystem.color.StatisticsColor
import com.mkulikov.taskb.feature.group.R
import com.mkulikov.taskb.group.screen.edit_group.Statistics

@Composable
internal fun GroupStatisticsBlock(
    statistics: Statistics,
) {
    Spacer(modifier = Modifier.height(30.dp))

    Text(
        text = "Statistics",
        style = MaterialTheme.typography.titleMedium
    )

    Spacer(modifier = Modifier.height(10.dp))

    if (statistics.isEmpty()) {
        Text(
            text = "No tasks in this group yet.",
            style = MaterialTheme.typography.bodyMedium
        )
    }

    if (statistics.active > 0) {
        StatisticsItem(
            text = pluralStringResource(
                id = R.plurals.active_tasks_count,
                count = statistics.active,
                statistics.active,
            ),
            color = StatisticsColor.Active.color,
        )
    }

    if (statistics.completed > 0) {
        StatisticsItem(
            text = pluralStringResource(
                id = R.plurals.completed_tasks_count,
                count = statistics.completed,
                statistics.completed,
            ),
            color = StatisticsColor.Completed.color,
        )
    }

    if (statistics.deleted > 0) {
        StatisticsItem(
            text = pluralStringResource(
                id = R.plurals.deleted_tasks_count,
                count = statistics.deleted,
                statistics.deleted,
            ),
            color = StatisticsColor.Deleted.color,
        )
    }
}