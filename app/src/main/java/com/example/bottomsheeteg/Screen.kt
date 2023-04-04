package com.example.bottomsheeteg

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


@Composable
fun Screen(
    name: String,
    btLabel: String,
    onClick: () -> Unit,
    switchBt: () -> Unit,
    openScreenBlocker:()->Unit,
) {
    Column {
        Text(text = "Hello $name!")
        Button(onClick = switchBt) {
            Text("Current Bt: $btLabel")
        }
        Button(onClick = {
            onClick()
        }) {
            Text(text = "Open bottom sheet")
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
