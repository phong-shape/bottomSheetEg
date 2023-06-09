package com.example.bottomsheeteg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bottomsheeteg.bottom_sheet_eg.BottomSheetAppState
import com.example.bottomsheeteg.bottom_sheet_eg.BottomSheetAppView
import com.example.bottomsheeteg.drawing.DrawingScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appState: BottomSheetAppState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomSheetAppView(state = appState)
//            DrawingScreen()
        }
    }
}
