package com.example.bottomsheeteg.bottom_sheet_eg

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.unit.dp
import com.example.bottomsheeteg.Screen
import com.example.bottomsheeteg.ScreenBlocker
import com.example.bottomsheeteg.bottom_sheet_eg.bt1.Bt1Actions
import com.example.bottomsheeteg.bottom_sheet_eg.bt1.Bt1State
import com.example.bottomsheeteg.bottom_sheet_eg.bt2.Bt2
import com.example.bottomsheeteg.bottom_sheet_eg.bt2.Bt2Actions
import com.example.bottomsheeteg.bottom_sheet_eg.bt2.Bt2State
import com.example.bottomsheeteg.bottom_sheet_eg.bt3.NestedBt3
import com.example.bottomsheeteg.bottom_sheet_eg.custom.ModalBottomSheetLayoutThatAdjustToItsContentSize
import com.example.bottomsheeteg.bottom_sheet_eg.bt1.Bt1
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetAppView(state: BottomSheetAppState) {

    val bt1Ms: MutableState<Bt1State> = state.bt1Ms
    val bt2Ms: MutableState<Bt2State> = state.bt2Ms
    val act1: Bt1Actions = state.act1
    val act2: Bt2Actions = state.act2
    var enableScreenBlocker by remember { mutableStateOf(false) }

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
//        confirmValueChange = { false },
        skipHalfExpanded = true,

        )
    val nestedBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
//        confirmValueChange = { false },
        skipHalfExpanded = true
    )
    val cc = rememberCoroutineScope()
    var showBt1 by remember { mutableStateOf(false) }
    var bt2LayoutCoors: LayoutCoordinates? by remember { mutableStateOf(null) }
    // get the height of the bt2 bottom sheet from its LayoutCoordinates
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        scrimColor = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            if (showBt1) {
                Bt1(
                    state = bt1Ms.value,
                    actions = act1
                )
            } else {
                ModalBottomSheetLayoutThatAdjustToItsContentSize(
                    sheetState = nestedBottomSheetState,
                    scrimColor = Color.Green.copy(alpha = 0.5f),
                    sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
                    sheetContent = {
                        NestedBt3()
                    },
                    adjustToContentSize = true,
                    content = {
                        Bt2(
                            state = bt2Ms.value,
                            actions = act2,
                            openNestedBt3 = {
                                cc.launch {
                                    nestedBottomSheetState.show()
                                }
                            },
                            onMeasure = {
                                bt2LayoutCoors = it
                            },
                            openScreenBlocker = {
                                enableScreenBlocker = true
                            }
                        )
                    }
                )
            }
        },
        content = {
            Screen(
                name = "Screen",
                openBts1 = {
                    cc.launch {
                        showBt1 = true
                        bottomSheetState.show()
                    }
                },
                openBts2 = {
                    cc.launch {
                        showBt1 = false
                        bottomSheetState.show()
                    }
                },
                openScreenBlocker = {
                    enableScreenBlocker = true
                }
            )
        }
    )
    ScreenBlocker(
        enable = enableScreenBlocker,
        toggle = {
            enableScreenBlocker = !enableScreenBlocker
        }
    )
}