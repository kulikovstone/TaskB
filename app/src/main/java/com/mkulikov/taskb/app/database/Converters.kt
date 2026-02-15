package com.mkulikov.taskb.app.database

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class Converters {

    @TypeConverter
    fun fromColor(color: Color): Int = color.toArgb()

    @TypeConverter
    fun toColor(value: Int): Color = Color(value)

    @TypeConverter
    fun fromLocalDateTime(localDateTime: LocalDateTime?): String? = localDateTime?.toString()

    @TypeConverter
    fun toLocalDateTime(value: String?): LocalDateTime? = value?.let { LocalDateTime.Companion.parse(it) }

    @TypeConverter
    fun fromLocalDate(localDate: LocalDate?): String? = localDate?.toString()

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? = value?.let { LocalDate.Companion.parse(it) }
}