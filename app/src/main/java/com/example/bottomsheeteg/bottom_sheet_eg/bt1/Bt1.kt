package com.example.bottomsheeteg.bottom_sheet_eg.bt1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bottomsheeteg.bottom_sheet_eg.PaddingBox


/**
 * Bottom sheet 1
 */
@Composable
fun Bt1(
    state: Bt1State,
    actions: Bt1Actions
) {
    Column(
        modifier = Modifier
            .width(440.dp)
            .padding(10.dp)
    ) {
        Text("Bt1")
        Row {
            BasicTextField(value = state.num1Text, onValueChange = {
                val newNum = it.toIntOrNull() ?: 0
                actions.updateNum1(newNum)
            })
            Text("+")
            BasicTextField(value = state.num2Text, onValueChange = {
                val newNum = it.toIntOrNull() ?: 0
                actions.updateNum2(newNum)
            })
            Text("=")
            if (state.total != null) {
                Text("${state.total}")
            }
        }

        Button(
            onClick = {
                actions.add()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = state.buttonColor
            )
        ) {
            Text("Add number")
        }

        PaddingBox(70)

    }
}