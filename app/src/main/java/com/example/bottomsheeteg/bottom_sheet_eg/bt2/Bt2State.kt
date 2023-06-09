package com.example.bottomsheeteg.bottom_sheet_eg.bt2

import androidx.compose.ui.graphics.Color
import com.example.bottomsheeteg.bottom_sheet_eg.bt1.Bt1State
import java.util.*

data class Bt2State(
    val text:String,
    val color1: Color,
    val color2: Color,
    val color3: Color,
    val isRun:Boolean
){
    companion object{
        fun random():Bt2State{
            return Bt2State(
                text = UUID.randomUUID().toString(),
                color1 = Bt1State.randomColor(),
                color2 = Bt1State.randomColor(),
                color3 = Bt1State.randomColor(),
                isRun = true,
            )
        }
    }

    fun next():Bt2State{
        if(this.isRun){
            return random().copy(
                isRun = this.isRun
            )
        }else{
            return this
        }
    }
}