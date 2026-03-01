package com.mkulikov.taskb.group.screen.group_list

import com.mkulikov.taskb.designsystem.color.GroupColor

internal fun getGroupLisMock() = GroupListState.Data(
    title = "All groups",
    groups = List(10) { index ->
        Group(
            id = index,
            name = listOf(
                "Работа",
                "Покупки",
                "TaskB",
                "Обучение",
                "Английский язык",
                "Переезд в Японию",
                "Покупка квартиры",
                "Хобби",
                "Тренировки",
                "Работа",
                "Здоровье",
                "Покупка участка",
            ).random(),
            color = GroupColor.random().color,
        )
    },
)