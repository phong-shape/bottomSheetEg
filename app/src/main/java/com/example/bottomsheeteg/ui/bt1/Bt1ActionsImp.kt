package com.example.bottomsheeteg.ui.bt1

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue

class Bt1ActionsImp(
    private val stateMs:MutableState<Bt1State>
) : Bt1Actions {
    private val state by stateMs
    override fun updateNum1(i: Int) {
        stateMs.value = state.copy(
            num1 = i
        )
    }

    override fun updateNum2(i: Int) {
        stateMs.value = state.copy(
            num2 = i
        )
    }

    override fun add() {
        stateMs.value = state.copy(
            total = state.num1+state.num2
        )
    }
}