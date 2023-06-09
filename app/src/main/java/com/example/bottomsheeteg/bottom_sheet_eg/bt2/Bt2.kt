package com.example.bottomsheeteg.bottom_sheet_eg.bt2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.example.bottomsheeteg.bottom_sheet_eg.PaddingBox
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
    onMeasure:(layoutCoors:LayoutCoordinates)->Unit,
    openScreenBlocker:()->Unit,
) {
    LaunchedEffect(key1 = state.isRun, block = {
        if(state.isRun){
            launch {
                while(true){
                    delay(200)
                    actions.randomizeState()
                }
            }
        }
    })


    Column(modifier = Modifier
        .onGloballyPositioned {
            onMeasure(it)
        }
        .padding(10.dp)
        .width(380.dp)
        .wrapContentHeight()
//        .border(2.dp,Color.Red)
    ) {
        Text("Bt2")
        Text(text = state.text)
        Row {
            Box(modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .background(state.color1))

            Divider(
                modifier = Modifier.width(8.dp),
                color = Color.Transparent,
            )

            Box(modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .background(state.color2))

            Divider(
                modifier = Modifier.width(8.dp),
                color = Color.Transparent,
            )

            Box(modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .background(state.color3))
        }

        Button(onClick={
            actions.toggleRandomizer()
        }){
            Text(if(state.isRun) "On" else "Off")
        }
        Row {
            Button(onClick={
                openNestedBt3()
            }){
                Text("Open nested bt3")
            }
            Divider(color = Color.Transparent, modifier = Modifier.size(5.dp))
            Button(onClick={
                openScreenBlocker()
            }){
                Text("Open screen blocker")
            }
        }

        PaddingBox()
    }
}