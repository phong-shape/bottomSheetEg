package com.example.bottomsheeteg.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.bottomsheeteg.Screen
import com.example.bottomsheeteg.ScreenBlocker
import com.example.bottomsheeteg.ui.bt1.Bt1Actions
import com.example.bottomsheeteg.ui.bt1.Bt1State
import com.example.bottomsheeteg.ui.bt2.Bt2
import com.example.bottomsheeteg.ui.bt2.Bt2Actions
import com.example.bottomsheeteg.ui.bt2.Bt2State
import com.example.bottomsheeteg.ui.bt3.NestedBt3
import com.example.bottomsheeteg.ui.theme.Bt1
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppView(state: AppState) {

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
    var hLayoutCoordinates: LayoutCoordinates? by remember { mutableStateOf(null) }
    val localDensity = LocalDensity.current
    // compute the height of the bt2 bottom sheet
    val bt2Height = if (bt2LayoutCoors != null && hLayoutCoordinates != null) {
        if (bt2LayoutCoors!!.isAttached && hLayoutCoordinates!!.isAttached) {
            with(localDensity) {
                bt2LayoutCoors!!.boundsInParent().height.toDp()
            }
        } else {
            800.dp
        }
    } else {
        800.dp
    }
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
                ModalBottomSheetLayout(
                    sheetState = nestedBottomSheetState,
                    scrimColor = Color.Green.copy(alpha = 0.5f),
                    sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
                    sheetContent = {
                        NestedBt3()
                    },
                    modifier = Modifier
                        .height(bt2Height)
                        .onGloballyPositioned {
                            hLayoutCoordinates = it
                        }
                ) {
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
            }
        }
    ) {

        Screen(
            name = "Screen",
            btLabel = if (showBt1) {
                "Bt1"
            } else {
                "Bt2"
            },
            onClick = {
                cc.launch {
                    bottomSheetState.show()
                }
            },
            switchBt = {
                showBt1 = !showBt1
            },
            openScreenBlocker = {
                enableScreenBlocker = true
            }
        )
    }
    ScreenBlocker(
        enable = enableScreenBlocker,
        toggle = {
            enableScreenBlocker = !enableScreenBlocker
        }
    )
}