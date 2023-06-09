package com.example.bottomsheeteg.drawing

import androidx.compose.runtime.Composable
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.atan2


@Composable
fun DrawingScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Green.copy(alpha = 0.5f))) {
        Box(
            Modifier
                .size(200.dp)
                .align(Alignment.Center)
                .border(1.dp,Color.Black)
                ) {
            Arc()
        }
    }


}

fun drawTicketPath(size: Size, cornerRadius: Float): Path {
    return Path().apply {
        reset()
        // Top left arc
        arcTo(
            rect = Rect(
                left = -cornerRadius,
                top = -cornerRadius,
                right = cornerRadius,
                bottom = cornerRadius
            ),
            startAngleDegrees = 90.0f,
            sweepAngleDegrees = -90.0f,
            forceMoveTo = false
        )
        lineTo(x = size.width - cornerRadius, y = 0f)
        // Top right arc
        arcTo(
            rect = Rect(
                left = size.width - cornerRadius,
                top = -cornerRadius,
                right = size.width + cornerRadius,
                bottom = cornerRadius
            ),
            startAngleDegrees = 180.0f,
            sweepAngleDegrees = -90.0f,
            forceMoveTo = false
        )
        lineTo(x = size.width, y = size.height - cornerRadius)
        // Bottom right arc
        arcTo(
            rect = Rect(
                left = size.width - cornerRadius,
                top = size.height - cornerRadius,
                right = size.width + cornerRadius,
                bottom = size.height + cornerRadius
            ),
            startAngleDegrees = 270.0f,
            sweepAngleDegrees = -90.0f,
            forceMoveTo = false
        )
        lineTo(x = cornerRadius, y = size.height)
        // Bottom left arc
        arcTo(
            rect = Rect(
                left = -cornerRadius,
                top = size.height - cornerRadius,
                right = cornerRadius,
                bottom = size.height + cornerRadius
            ),
            startAngleDegrees = 0.0f,
            sweepAngleDegrees = -90.0f,
            forceMoveTo = false
        )
        lineTo(x = 0f, y = cornerRadius)
        close()
    }
}

@Composable
fun Arc(
    percentage: Double = 0.3,
    color: Color = Color.Red,
    width: Float = 60f
) {

    val px = LocalDensity.current.run{
        12.dp.toPx()
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
//        drawArc(
//            color = color,
//            startAngle = 0f,
//            sweepAngle = 45f,
//            useCenter = false,
//            style = Stroke(30.dp.toPx(), cap = StrokeCap.Square),
//            size = Size(size.width, size.height)
//        )

        var outLine = Outline.Generic(
            drawTicketPath(
                Size(200f, 100f), 30f
            )
        )

        val diameter = size.minDimension
        val radius = diameter/2

        outLine = Outline.Generic(Path().apply {
            this.
            reset()

            // point x
            moveTo(
                x = diameter,
                y = radius
            )
            // 1
            lineTo(
                x= diameter - width,
                y = radius
            )
            //2
            lineTo(
                x=radius,
                y=width,
            )
            //3
            lineTo(
                x=radius,
                y=0f
            )
            //4
            lineTo(
                x=diameter,
                y=radius
            )
//            drawArc()
//            arcTo(
//                rect = Rect(
//                    offset = Offset(
//                        x=0f,
//                        y=0f,
//                    ),
//                    size=Size(
//                        width = diameter-width,
//                        height = diameter-width,
//                    )
//                ),
//                startAngleDegrees = 0f,
//                sweepAngleDegrees = -45f,
//                forceMoveTo = true,
//            )



            close()
        })
        drawOutline(outline = outLine, color)
    }
}



@Composable
fun DoughnutChart1(
    values: List<Float> = listOf(10f, 20f, 40f, 30f),
    colors: List<Color> = listOf(
        Color(0xFFDAA98D),
        Color(0xFF024D54),
        Color(0xFFC6652F),
        Color(0xFF3F51B5),
        Color(0xFFDAA98D),
        Color(0xFF024D54),
        Color(0xFFC6652F),
        Color(0xFF3F51B5),
    ),
    size: Dp = 150.dp,
    thickness: Dp = 50.dp
) {

    var selectedIndex by remember { mutableStateOf(-1) }
    var touchPosition by remember { mutableStateOf<Offset?>(null) }

    LaunchedEffect(key1 = values) {
        selectedIndex = -1
        touchPosition = null
    }

    // Sum of all the values
    val sumOfValues = values.sum()

    // Calculate each proportion
    val proportions = values.map {
        it * 100 / sumOfValues
    }

    // Convert each proportion to angle
    val sweepAngles = proportions.map {
        360 * it / 100
    }

    val initialAngle = -90f

//    Log.d("DoughnutChart", "values: $values")
    Log.d("DoughnutChart", "sweepAngles: $sweepAngles")

//    val thicknessPx = thickness.toPx()
    val thicknessPx = thickness.value
    val thicknessPxSelected = thicknessPx * 1.1f

    Canvas(
        modifier = Modifier
            .size(size = size)
            .background(Color.Green.copy(alpha = 0.1f))
            // Provide a slight opacity to for compositing into an
            // offscreen buffer to ensure blend modes are applied to empty pixel information
            // By default any alpha != 1.0f will use a compositing layer by default
            .graphicsLayer(alpha = 0.997f)
            .pointerInput(true) {
                detectTapGestures { offset ->

                    touchPosition = offset
                    Log.d("DoughnutChart Tap", "Tapped offset = $offset")
                    Log.d(
                        "DoughnutChart Tap",
                        "Tapped size = ${this.size}, thicknessPx: $thicknessPx"
                    )

                    val validWidth =
                        (0 > offset.x && offset.x < thicknessPx) || (this.size.width - thicknessPx < offset.x && offset.x < this.size.width)
                    val validHeight =
                        (0 > offset.y && offset.y < thicknessPx) || (this.size.height - thicknessPx < offset.y && offset.y < this.size.height)

                    if (!validWidth && !validHeight) {
                        selectedIndex = -1
                        return@detectTapGestures
                    }

                    var tapAngleInDegrees = (atan2(
                        (offset.y - (size.toPx() / 2)).toDouble(),
                        (offset.x - (size.toPx() / 2)).toDouble()
                    ) * (180 / Math.PI).toFloat()).mod(360f)

                    if (tapAngleInDegrees > 270) {
                        tapAngleInDegrees -= 360f
                    }

                    Log.d("DoughnutChart Tap", "Tapped tapAngleInDegrees = $tapAngleInDegrees")

                    selectedIndex = findTappedSliceIndex(
                        tapAngleInDegrees,
                        sweepAngles
                    )

                }
            }
    ) {

        // thickness = 10.dp
        // thicknessSelected = thickness * 1.1f
        // selectedScale = defaultScale * 1.01f
        // availableArea = 367.75
        // selectedScale: 0.9427094


        val availableArea = this.size.width - thicknessPx + 1.4f
        Log.d("DoughnutChart", "availableArea: $availableArea")
        val defaultScale = availableArea / this.size.width
        val selectedScale = defaultScale * 1.05f
        Log.d("DoughnutChart", "selectedScale: $selectedScale")
        var startAngle = initialAngle
//        with(drawContext.canvas.nativeCanvas) {
//            val checkPoint = saveLayer(null, null)
        for (i in values.indices) {

            val strokeScale = if (selectedIndex == i) thicknessPxSelected else thicknessPx
//            val scale = defaultScale
            val scale = if (selectedIndex == i) selectedScale else defaultScale

            Log.d(
                "DoughnutChart",
                "value: ${values[i]} , sweepAngles: ${sweepAngles[i]} , selectedValue: $selectedIndex , scale: $scale"
            )

            scale(scale) {

                drawArc(
                    color = colors[i],
                    startAngle = startAngle,
                    sweepAngle = sweepAngles[i],
                    useCenter = false,
                    style = Stroke(
                        width = thicknessPx,
                        cap = StrokeCap.Butt
                    )
                )

            }


            startAngle += sweepAngles[i]

        }

        startAngle = initialAngle


//            drawCircle(
//                color = Color.Black,
//                radius = 80f,
//                center = center,
//                blendMode = BlendMode.SrcOut
//            )


//            restoreToCount(checkPoint)
//        }

        if (touchPosition != null) {
            drawCircle(
                color = Color.Red,
                radius = 10f,
                center = touchPosition!!
            )
        }

    }
}


private fun findTappedSliceIndex(
    tapAngleInDegrees: Double,
    sweepAngles: List<Float>,
): Int {

    var currentAngle = -90f

    sweepAngles.forEachIndexed { index, it ->

        currentAngle += it

        Log.d("DoughnutChart Tap", "currentAngle = $currentAngle")
        if (tapAngleInDegrees < currentAngle) {
            return index
        }

    }

    return -1
}

fun generateRandomPieChartData(slices: Int = 3): List<Float> {
    val random = java.util.Random()
    val values = mutableListOf<Float>()
    for (i in 0 until slices) {
        values.add(1 + random.nextInt(99).toFloat())
    }
    return values
}