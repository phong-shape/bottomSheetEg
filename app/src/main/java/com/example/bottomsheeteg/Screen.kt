package com.example.bottomsheeteg

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


@Composable
fun Screen(
    name: String,
    openBts1: () -> Unit,
    openBts2: () -> Unit,
    openScreenBlocker:()->Unit,
) {
    Column {
        Text(text = "Hello $name!")
        Button(onClick = openBts2) {
            Text("Open bottom sheet 2")
        }
        Button(onClick = {
            openBts1()
        }) {
            Text(text = "Open bottom sheet 1")
        }
        Button(
            onClick = {
                openScreenBlocker()
            }
        ){
            Text("Open screen blocker")
        }
    }
}
