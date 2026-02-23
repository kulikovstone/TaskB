package com.mkulikov.taskb.designsystem.old.part

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
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

@Composable
fun ChangeColorDialog(
    onDismiss: () -> Unit,
    onSelect: (Color) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.wrapContentSize(),
            shape = MaterialTheme.shapes.large,
        ) {
            Column(modifier = Modifier.padding(32.dp)) {
                Row {
                    ColoredCircle(Color.Transparent) { onSelect(it) }
                    ColoredCircle(red400) { onSelect(it) }
                    ColoredCircle(pink400) { onSelect(it) }
                    ColoredCircle(purple400) { onSelect(it) }
                    ColoredCircle(deepPurple400) { onSelect(it) }
                    ColoredCircle(indigo400) { onSelect(it) }
                    ColoredCircle(blue400) { onSelect(it) }
                    ColoredCircle(lightBlue400) { onSelect(it) }
                    ColoredCircle(cyan400) { onSelect(it) }
                    ColoredCircle(teal400) { onSelect(it) }

                }
                Row {
                    ColoredCircle(green400) { onSelect(it) }
                    ColoredCircle(lightGreen400) { onSelect(it) }
                    ColoredCircle(lime400) { onSelect(it) }
                    ColoredCircle(yellow400) { onSelect(it) }
                    ColoredCircle(amber400) { onSelect(it) }
                    ColoredCircle(orange400) { onSelect(it) }
                    ColoredCircle(deepOrange400) { onSelect(it) }
                    ColoredCircle(brown400) { onSelect(it) }
                    ColoredCircle(grey400) { onSelect(it) }
                    ColoredCircle(blueGrey400) { onSelect(it) }
                }
            }
        }
    }
}

@Composable
private fun ColoredCircle(
    color: Color,
    onClick: (Color) -> Unit,
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .padding(1.dp)
            .clip(CircleShape)
            .background(color = color)
            .border(1.dp, Color.Black)
            .clickable { onClick(color) }
    )
}