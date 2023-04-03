package com.example.bottomsheeteg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bottomsheeteg.ui.bt1.Bt1Actions
import com.example.bottomsheeteg.ui.bt1.Bt1State
import com.example.bottomsheeteg.ui.bt2.Bt2State
import com.example.bottomsheeteg.ui.theme.Bt1
import com.example.bottomsheeteg.ui.bt2.Bt2
import com.example.bottomsheeteg.ui.bt2.Bt2Actions
import com.example.bottomsheeteg.ui.bt3.NestedBt3
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var bt1Ms: MutableState<Bt1State>

    @Inject
    lateinit var bt2Ms: MutableState<Bt2State>

    @Inject
    lateinit var act1: Bt1Actions

    @Inject
    lateinit var act2: Bt2Actions

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val modalSheetState = rememberModalBottomSheetState(
                initialValue = ModalBottomSheetValue.Hidden,
                confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
                skipHalfExpanded = true
            )
            val nestedModalSheetState = rememberModalBottomSheetState(
                initialValue = ModalBottomSheetValue.Hidden,
                confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
                skipHalfExpanded = true
            )
            val cc = rememberCoroutineScope()
            var showBt1 by remember { mutableStateOf(false) }
            ModalBottomSheetLayout(
                sheetState = modalSheetState,
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
                            sheetState = nestedModalSheetState,
                            scrimColor = Color.Red.copy(alpha = 0.5f),
                            sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
                            sheetContent = {
                                NestedBt3()
                            },
                            // must explicitly set the height constraint to 800dp,
                            // otherwise the nested bottom sheet layout will take up the entire screen
                            modifier = Modifier.height(800.dp)
                        ) {
                            Bt2(
                                state = bt2Ms.value,
                                actions = act2,
                                openNestedBt3 = {
                                    cc.launch {
                                        nestedModalSheetState.show()
                                    }
                                }
                            )
                        }
                    }
                }
            ) {
                Screen(
                    name = "Screen",
                    bt = if (showBt1) {
                        "Bt1"
                    } else {
                        "Bt2"
                    },
                    onClick = {
                        cc.launch {
                            modalSheetState.show()
                        }
                    },
                    switchBt = {
                        showBt1 = !showBt1
                    }
                )
            }
        }
    }
}
