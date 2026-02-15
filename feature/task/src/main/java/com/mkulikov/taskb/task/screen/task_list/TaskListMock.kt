package com.mkulikov.taskb.task.screen.task_list

import androidx.compose.ui.graphics.Color

fun taskListMock() = TaskListState.Data(
    title = "Все задачи",
    filtered = false,
    tasks = listOf(
        Task(
            id = 0,
            name = "Задача первая",
            color = Color.Blue,
            isCompleted = true,
        ),
        Task(
            id = 1,
            name = "Еще задача",
            color = Color.Green,
            isCompleted = false,
        ),
        Task(
            id = 2,
            name = "Последняя задача",
            isCompleted = false,
        ),
        Task(
            id = 3,
            name = "Задача первая",
            color = Color.Blue,
            isCompleted = true,
        ),
        Task(
            id = 4,
            name = "Еще задача",
            color = Color.Green,
            isCompleted = false,
        ),
        Task(
            id = 5,
            name = "Последняя задача",
            isCompleted = false,
        ),
        Task(
            id = 6,
            name = "Задача первая",
            color = Color.Blue,
            isCompleted = true,
        ),
        Task(
            id = 7,
            name = "Еще задача",
            color = Color.Green,
            isCompleted = false,
        ),
        Task(
            id = 8,
            name = "Последняя задача",
            isCompleted = false,
        ),
        Task(
            id = 9,
            name = "Задача первая",
            color = Color.Blue,
            isCompleted = true,
        ),
        Task(
            id = 10,
            name = "Еще задача",
            color = Color.Green,
            isCompleted = false,
        ),
        Task(
            id = 11,
            name = "Последняя задача",
            isCompleted = false,
        ),
        Task(
            id = 12,
            name = "Последняя задача",
            isCompleted = false,
        ),
        Task(
            id = 13,
            name = "Последняя задача",
            isCompleted = false,
        ),
        Task(
            id = 14,
            name = "Последняя задача",
            isCompleted = false,
        ),
        Task(
            id = 15,
            name = "Последняя задача",
            isCompleted = false,
        ),
        Task(
            id = 16,
            name = "Последняя задача",
            isCompleted = false,
        ),
        Task(
            id = 17,
            name = "Последняя задача",
            isCompleted = false,
        ),
        Task(
            id = 18,
            name = "Последняя задача",
            isCompleted = false,
        ),
        Task(
            id = 19,
            name = "Последняя задача",
            isCompleted = false,
        ),
    )
)