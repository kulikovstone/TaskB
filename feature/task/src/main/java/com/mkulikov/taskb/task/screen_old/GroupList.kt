package com.mkulikov.taskb.task.screen_old

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.DoneAll
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.GroupOff
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mkulikov.taskb.core.designsystem.old.element.GroupItem
import com.mkulikov.taskb.core.designsystem.old.element.getGroupBackgroundColor
import com.mkulikov.taskb.core.designsystem.theme.grey200
import com.mkulikov.taskb.designsystem.theme.PhoenixTheme
import com.mkulikov.taskb.feature.task_data.database.Group
import com.mkulikov.taskb.task.screen_old.main.Counts
import com.mkulikov.taskb.task.screen_old.main.MainIntent
import com.mkulikov.taskb.task.screen_old.main.TaskFilter

@Composable
fun GroupList(
    groups: List<Group>,
    selectedGroup: Group?,
    counts: Counts,
    filter: TaskFilter,
    intent: (MainIntent) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
//        Title("Группы")

        GroupPanel(
            selectedGroup = selectedGroup,
            intent = intent,
        )

        DefaultGroups(
            counts = counts,
            filter = filter,
            onClick = { intent(MainIntent.SelectDefaultGroup(it)) }
        )

        Divider()

        LazyColumn(
            modifier = Modifier.weight(1f),
        ) {
            items(items = groups, key = { it.id }) { group ->
                GroupItem(
                    name = group.name,
                    color = group.color,
                    count = counts.group.getOrDefault(group.id, 0),
                    isSelected = selectedGroup?.id == group.id,
                    select = { intent(MainIntent.SelectGroup(group)) },
                )
            }
        }
    }
}

@Composable
private fun Title(
    title: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.W500,
            color = PhoenixTheme.customColor.listTitleColor,
        )
    }
}

@Composable
private fun Divider() {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(grey200)
        )
    }
}

@Composable
private fun GroupPanel(
    selectedGroup: Group?,
    intent: (MainIntent) -> Unit,
) {
    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 16.dp),
                text = "Группы",
                fontWeight = FontWeight.W500,
                color = PhoenixTheme.customColor.listTitleColor,
            )

            selectedGroup?.let {
                IconButton(
                    onClick = { intent(MainIntent.EditGroup(selectedGroup)) },
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = null,
                    )
                }
            }

            IconButton(
                onClick = { intent(MainIntent.AddGroup) },
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
private fun DefaultGroups(
    counts: Counts,
    filter: TaskFilter,
    onClick: (TaskFilter) -> Unit,
) = Column {
    DefaultGroup(
        name = "Все задачи",
        icon = Icons.Outlined.Task,
        count = counts.uncompleted,
        isSelected = filter == TaskFilter.Uncompleted,
        onClick = { onClick(TaskFilter.Uncompleted) }
    )
    DefaultGroup(
        name = "Сегодня",
        icon = Icons.Outlined.Today,
        count = counts.today,
        isSelected = filter == TaskFilter.Today,
        onClick = { onClick(TaskFilter.Today) }
    )
    DefaultGroup(
        name = "Без группы",
        icon = Icons.Outlined.GroupOff,
        count = counts.withoutGroup,
        isSelected = filter == TaskFilter.WithoutGroup,
        onClick = { onClick(TaskFilter.WithoutGroup) }
    )
    DefaultGroup(
        name = "Завершенные",
        icon = Icons.Outlined.DoneAll,
        isSelected = filter == TaskFilter.Completed,
        onClick = { onClick(TaskFilter.Completed) }
    )
}

@Composable
fun DefaultGroup(
    name: String,
    icon: ImageVector,
    count: Int = 0,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(getGroupBackgroundColor(isSelected))
            .clickable(onClick = onClick),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = icon,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                modifier = Modifier.weight(1f),
                text = name,
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