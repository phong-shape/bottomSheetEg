package com.example.bottomsheeteg.bottom_sheet_eg.custom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

/**
 * A modal bottom sheet that can adjust to its [content]'s size.
 * [adjustToContentSize]: tell the modal bottom sheet to adjust its size to its content size or not. Default to true.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalBottomSheetLayoutThatAdjustToItsContentSize(
    sheetContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    sheetState: ModalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden),
    sheetShape: Shape = MaterialTheme.shapes.large,
    sheetElevation: Dp = ModalBottomSheetDefaults.Elevation,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    sheetContentColor: Color = contentColorFor(sheetBackgroundColor),
    scrimColor: Color = ModalBottomSheetDefaults.scrimColor,
    adjustToContentSize: Boolean = true,
    content: @Composable () -> Unit,
) {
    var contentLayoutCoors: LayoutCoordinates? by remember { mutableStateOf(null) }
    val density = LocalDensity.current
    val sizeModifier = contentLayoutCoors?.let { clc ->
        if (clc.isAttached && adjustToContentSize) {
            val height = with(density) {
                clc.size.height.toDp()
            }
            val width = with(density) {
                clc.size.width.toDp()
            }
            Modifier
                .height(height)
                .width(width)
        } else {
            Modifier
        }
    } ?: Modifier

    ModalBottomSheetLayout(
        sheetContent,
        modifier = Modifier
            .then(sizeModifier)
            .then(modifier),
        sheetState, sheetShape,
        sheetElevation, sheetBackgroundColor,
        sheetContentColor, scrimColor,
        content = {
            Box(
                modifier = Modifier.onGloballyPositioned {
                    contentLayoutCoors = it
                }
            ) {
                content()
            }
        }
    )
}