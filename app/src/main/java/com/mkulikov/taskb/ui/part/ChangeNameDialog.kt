package com.mkulikov.taskb.ui.part

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeNameDialog(
    title: String,
    currentName: String,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
) {
    val name by remember { mutableStateOf(currentName) }


    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = MaterialTheme.shapes.large,
        ) {
            Text(
                text = "This is a minimal dialog",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )

            Text(
                text = "Text",
                fontSize = 16.sp,
            )
        }
    }

//    BasicAlertDialog(onDismissRequest = onDismiss) {
//        Surface(
//            modifier = Modifier.wrapContentWidth().wrapContentHeight(),
//            shape = MaterialTheme.shapes.large,
//            tonalElevation = AlertDialogDefaults.TonalElevation
//        ) {
//            Column(modifier = Modifier.padding(16.dp)) {
//                Text(text = "This area typically contains the supportive text " + "which presents the details regarding the Dialog's purpose.")
//                Spacer(modifier = Modifier.height(24.dp))
//                TextButton(
//                    modifier = Modifier.align(Alignment.End),
//                    onClick = { onSave(name) }
//                ) {
//                    Text("Confirm")
//                }
//            }
//        }

//        Dialog(
//            onDismissRequest = onDismiss
//        ) {
//            Column(
//                modifier = Modifier
//                    .width(300.dp)
//                    .padding(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    modifier = Modifier.padding(8.dp),
//                    text = title,
//                )
//                TextField(
//                    value = name,
//                    onValueChange = { name = it },
//                    label = { Text("Введите название") }
//                )
//                Button(
//                    onClick = { onSave(name) },
//                    modifier = Modifier.padding(top = 16.dp)
//                ) {
//                    Text("Сохранить")
//                }
//            }
//        }
}