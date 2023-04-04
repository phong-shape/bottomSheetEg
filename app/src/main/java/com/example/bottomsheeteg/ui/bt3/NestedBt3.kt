package com.example.bottomsheeteg.ui.bt3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


/**
 * Bottom sheet 3, nested in Bottom sheet 2
 */
@Composable
fun NestedBt3() {
    Box(
         modifier = Modifier.fillMaxWidth().height(200.dp).background(Color.Red.copy(alpha=0.5f))
    ){
        Text("Nested Bt3", modifier = Modifier.align(Alignment.Center))
    }
}