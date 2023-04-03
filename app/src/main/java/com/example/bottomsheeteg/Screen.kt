package com.example.bottomsheeteg

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


@Composable
fun Screen(
    name: String,
    bt: String,
    onClick: () -> Unit,
    switchBt: () -> Unit,
) {
    Column {
        Text(text = "Hello $name!")
        Button(onClick = switchBt) {
            Text("Current Bt: $bt")
        }
        Button(onClick = {
            onClick()
        }) {
            Text(text = "Open bottom sheet")
        }
    }
}
