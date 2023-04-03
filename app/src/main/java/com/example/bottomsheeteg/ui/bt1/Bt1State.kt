package com.example.bottomsheeteg.ui.bt1

import androidx.compose.ui.graphics.Color
import java.util.*
import kotlin.random.Random

data class Bt1State(
    val title:String,
    val buttonColor: Color,
    val num1:Int,
    val num2:Int,
    val total:Int? = null,
) {
    companion object{
        fun randomColor():Color{
            val rnd = Random
            return Color(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        }
        fun random():Bt1State{
            val rnd = Random
            return Bt1State(
                title = "Add 2 number: ${UUID.randomUUID().toString().substring(1 .. 4)}",
                buttonColor = randomColor(),
                num1 = rnd.nextInt(10),
                num2 = rnd.nextInt(20),
            )
        }
    }
}