package com.example.bottomsheeteg.ui

import androidx.compose.runtime.MutableState
import com.example.bottomsheeteg.ui.bt1.Bt1Actions
import com.example.bottomsheeteg.ui.bt1.Bt1State
import com.example.bottomsheeteg.ui.bt2.Bt2Actions
import com.example.bottomsheeteg.ui.bt2.Bt2State
import javax.inject.Inject

class AppStateImp @Inject constructor(
    override val bt1Ms: MutableState<Bt1State>,
    override val bt2Ms: MutableState<Bt2State>,
    override val act1: Bt1Actions,
    override val act2: Bt2Actions,
): AppState