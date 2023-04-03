package com.example.bottomsheeteg.ui.bt2

import androidx.compose.ui.graphics.Color
import com.example.bottomsheeteg.ui.bt1.Bt1State
import java.util.*

data class Bt2State(
    val text:String,
    val color: Color,
    val isRun:Boolean
){
    companion object{
        fun random():Bt2State{
            return Bt2State(
                text = UUID.randomUUID().toString(),
                color = Bt1State.randomColor(),
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