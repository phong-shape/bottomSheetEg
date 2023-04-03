package com.example.bottomsheeteg.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bottomsheeteg.ui.PaddingBox
import com.example.bottomsheeteg.ui.bt1.Bt1Actions
import com.example.bottomsheeteg.ui.bt1.Bt1State

@Composable
fun Bt1(
    state: Bt1State,
    actions: Bt1Actions
) {
        Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Row {
                BasicTextField(value = "${state.num1}", onValueChange = {
                    val newNum = it.toIntOrNull()
                    if (newNum != null) {
                        actions.updateNum1(newNum)
                    }
                })
                Text("+")
                BasicTextField(value = "${state.num2}", onValueChange = {
                    val newNum = it.toIntOrNull()
                    if (newNum != null) {
                        actions.updateNum2(newNum)
                    }
                })
                Text("=")
                if(state.total!=null){
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

            PaddingBox()

        }
}