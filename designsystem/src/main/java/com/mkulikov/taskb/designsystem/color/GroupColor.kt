package com.mkulikov.taskb.designsystem.color

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.mkulikov.taskb.designsystem.R
import com.mkulikov.taskb.designsystem.theme.amber400
import com.mkulikov.taskb.designsystem.theme.blue400
import com.mkulikov.taskb.designsystem.theme.blueGrey400
import com.mkulikov.taskb.designsystem.theme.brown400
import com.mkulikov.taskb.designsystem.theme.cyan400
import com.mkulikov.taskb.designsystem.theme.deepOrange400
import com.mkulikov.taskb.designsystem.theme.deepPurple400
import com.mkulikov.taskb.designsystem.theme.green400
import com.mkulikov.taskb.designsystem.theme.grey400
import com.mkulikov.taskb.designsystem.theme.indigo400
import com.mkulikov.taskb.designsystem.theme.lightBlue400
import com.mkulikov.taskb.designsystem.theme.lightGreen400
import com.mkulikov.taskb.designsystem.theme.lime400
import com.mkulikov.taskb.designsystem.theme.orange400
import com.mkulikov.taskb.designsystem.theme.pink400
import com.mkulikov.taskb.designsystem.theme.purple400
import com.mkulikov.taskb.designsystem.theme.red400
import com.mkulikov.taskb.designsystem.theme.teal400
import com.mkulikov.taskb.designsystem.theme.yellow400
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

enum class GroupColor(
    val color: Color,
    @StringRes val colorNameRes: Int
) {
    Transparent(Color.Transparent, R.string.transparent),

    Red(red400, R.string.red),
    Pink(pink400, R.string.pink),
    Purple(purple400, R.string.purple),
    DeepPurple(deepPurple400, R.string.deep_purple),
    Indigo(indigo400, R.string.indigo),
    Blue(blue400, R.string.blue),
    LightBlue(lightBlue400, R.string.light_blue),
    Cyan(cyan400, R.string.cyan),
    Teal(teal400, R.string.teal),
    Green(green400, R.string.green),
    LightGreen(lightGreen400, R.string.light_green),
    Lime(lime400, R.string.lime),
    Yellow(yellow400, R.string.yellow),
    Amber(amber400, R.string.amber),
    Orange(orange400, R.string.orange),
    DeepOrange(deepOrange400, R.string.deep_orange),
    Brown(brown400, R.string.brown),
    Grey(grey400, R.string.grey),
    BlueGrey(blueGrey400, R.string.blue_grey);

    companion object {

        private val colorMap: Map<Color, GroupColor> by lazy {
            entries.associateBy { it.color }
        }

        fun getAllColors(): ImmutableList<GroupColor> = entries.toImmutableList()

        fun random(): GroupColor = getAllColors().random()

        fun fromColor(color: Color): GroupColor {
            return colorMap[color] ?: Transparent
        }
    }
}
