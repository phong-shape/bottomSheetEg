package com.example.bottomsheeteg.ui.bt2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bottomsheeteg.ui.PaddingBox
import com.example.bottomsheeteg.ui.bt2.Bt2Actions
import com.example.bottomsheeteg.ui.bt2.Bt2State
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Bottom sheet 2
 */
@Composable
fun Bt2(
    state:Bt2State,
    actions: Bt2Actions,
    openNestedBt3:()->Unit,
) {
    LaunchedEffect(key1 = state.isRun, block = {
        if(state.isRun){
            launch {
                while(true){
                    delay(500)
                    actions.randomizeState()
                }
            }
        }
    })


    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = state.text)
        Box(modifier = Modifier
            .width(30.dp)
            .height(30.dp)
            .background(state.color))
        Button(onClick={
            actions.switch()
        }){
            Text(if(state.isRun) "On" else "Off")
        }

        Button(onClick={
            openNestedBt3()
        }){
            Text("Open nested bt3")
        }

        PaddingBox()
    }
}