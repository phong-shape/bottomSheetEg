package com.example.bottomsheeteg.bottom_sheet_eg.bt2

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue

class Bt2ActionsImp(
    val bt2StateMs:MutableState<Bt2State>
): Bt2Actions {
    val state by bt2StateMs
    override fun toggleRandomizer() {
        bt2StateMs.value = state.copy(
            isRun = !state.isRun
        )
    }

    override fun randomizeState() {
        bt2StateMs.value = state.next()
    }
}