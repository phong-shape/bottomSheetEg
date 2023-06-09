package com.example.bottomsheeteg.bottom_sheet_eg

import androidx.compose.runtime.MutableState
import com.example.bottomsheeteg.bottom_sheet_eg.bt1.Bt1Actions
import com.example.bottomsheeteg.bottom_sheet_eg.bt1.Bt1State
import com.example.bottomsheeteg.bottom_sheet_eg.bt2.Bt2Actions
import com.example.bottomsheeteg.bottom_sheet_eg.bt2.Bt2State

interface BottomSheetAppState{
    val bt1Ms: MutableState<Bt1State>
    val bt2Ms: MutableState<Bt2State>
    val act1: Bt1Actions
    val act2: Bt2Actions
}