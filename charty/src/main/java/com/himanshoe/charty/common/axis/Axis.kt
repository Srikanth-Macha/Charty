package com.himanshoe.charty.common.axis

import android.graphics.Paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import java.text.DecimalFormat

internal fun DrawScope.xAxis(axisConfig: AxisConfig, maxValue: Float) {
    val graphYAxisEndPoint = size.height.div(4)
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(40f, 20f), 0f)
    val labelChunck = maxValue.div(4)

    repeat(5) { index ->
        val yAxisEndPoint = graphYAxisEndPoint.times(index)
        if (axisConfig.showUnitLabels) {
            drawIntoCanvas {
                it.nativeCanvas.apply {
                    drawText(
                        getLabelText(labelChunck.times(4.minus(index))),
                        0F.minus(25),
                        yAxisEndPoint.minus(10),
                        Paint().apply {
                            textSize = size.width.div(30)
                            textAlign = Paint.Align.CENTER
                        }
                    )
                }
            }
        }
        if (index != 0) {
            drawLine(
                start = Offset(x = 0f, y = yAxisEndPoint),
                end = Offset(x = size.width, y = yAxisEndPoint),
                color = axisConfig.xAxisColor,
                pathEffect = pathEffect,
                alpha = 0.2F,
                strokeWidth = size.width.div(200)
            )
        }
    }
}

internal fun DrawScope.yAxis(axisConfig: AxisConfig) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(40f, 20f), 0f)
    drawLine(
        start = Offset(x = 0f, y = 0F),
        end = Offset(x = 0F, y = size.height),
        color = axisConfig.yAxisColor,
        pathEffect = pathEffect,
        alpha = 0.2F,
        strokeWidth = size.width.div(200)
    )
}

private fun getLabelText(value: Float) = DecimalFormat("#.##").format(value).toString()
