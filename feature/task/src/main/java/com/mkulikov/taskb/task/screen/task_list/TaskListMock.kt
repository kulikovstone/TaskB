package com.mkulikov.taskb.task.screen.task_list

import com.mkulikov.taskb.designsystem.color.GroupColor
import kotlin.random.Random

internal fun getTaskListMock() = TaskListState.Data(
    title = "All tasks",
    filtered = false,
    tasks = List(35) { index ->
        Task(
            id = index,
            name = listOf(
                "Важная задача",
                "Купить хлеб",
                "Помыть машину",
                "Визит к стоматологу",
                "Снять доллары",
                "Непонятная задача с очень длинным названием",
                "Тренировка",
                "Фикс бага",
                "Настройка роутера",
                "Бывают ли белые белки?",
                "Оплатить интернет",
                "Поспать после обеда",
            ).random(),
            color = listOf(
                GroupColor.random().color,
                GroupColor.random().color,
                GroupColor.random().color,
                GroupColor.random().color,
                null,
                null,
                null,
                null,
            ).random(),
            isCompleted = Random.nextBoolean(),
        )
    },
)