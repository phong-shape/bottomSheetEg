package com.example.bottomsheeteg.bottom_sheet_eg

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
@Composable
fun PaddingBox(height:Int = 500) {
    Box(Modifier.fillMaxWidth().height(height.dp).clip(RoundedCornerShape(12.dp)).background(
            Color.Gray)){
        Text("Padding box",modifier = Modifier.align(Alignment.Center))
    }
}