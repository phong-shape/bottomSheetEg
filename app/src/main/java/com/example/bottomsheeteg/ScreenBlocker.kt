package com.example.bottomsheeteg

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScreenBlocker(
    enable:Boolean,
    toggle:()->Unit,
) {
   if(enable){
       Box(
           modifier = Modifier.pointerInteropFilter {
               // to block touch event from going through this screen blocker
               false
           }

       ){
           Column(
               modifier = Modifier.background(Color.Transparent)
           ) {
               Box(
                   modifier = Modifier
                       .height(300.dp)
                       .fillMaxWidth()
                       .background(Color.LightGray.copy(alpha = 0.6f))
               )
               Box(
                   modifier = Modifier
                       .weight(1.0f)
                       .background(Color.White)
                       .fillMaxSize()
               ) {
                   Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                       Text("I'm blocking the screen. You can't disable me")
                       Button(
                           onClick = {
                               toggle()
                           }
                       ) {
                           Text("Disable him")
                       }
                   }
               }
           }
       }
   }
}