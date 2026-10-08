package com.example.drawingapp.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.drawingapp.model.BrushType
import com.example.drawingapp.viewmodel.DrawingViewModel

@Composable
fun CanvasScreen(myNavController : NavHostController, drawingVM: DrawingViewModel, drawingID: Int){
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            "Canvas Screen",
            modifier = Modifier.testTag("canvas_screen")
        )

        Button(onClick = {myNavController.navigate("PenScreen/$drawingID")}){
            Text("Pen")
        }

        DrawingCanvas(drawingVM, drawingID)

        Button(onClick = {
            drawingVM.addDrawing("Untitled") { id ->
                myNavController.navigate("canvasScreen/$id")
            }
        }) {
            Text("Start Drawing")
        }
    }
}

// Adapted from DrawingCanvas from DrawingDemoV2
@Composable
fun DrawingCanvas(viewModel: DrawingViewModel, drawingId: Int) {
    val points by viewModel.points.collectAsState()

    Canvas(
        modifier = Modifier
            .size(300.dp)
            .background(Color.LightGray)
            .clipToBounds() // Prevent drawing outside of bounds
            .pointerInput(viewModel, drawingId) {
                // Stroke ID for tracking undo eventually
                var strokeId = viewModel.points.value
                    .maxOfOrNull { it.strokeId } ?: 0

                detectDragGestures(
                    onDragStart = { offset ->
                        // Start a new stroke
                        strokeId++
                        viewModel.addPoint(drawingId, strokeId, offset.x, offset.y)
                    },
                    onDrag = { change, _ ->
                        change.consume()
                        // Add another point to the same stroke
                        viewModel.addPoint(
                            drawingId,
                            strokeId,
                            change.position.x,
                            change.position.y
                        )
                    }
                )
            }
    ) {
        // Keep this drawing's points and group them into separate strokes.
        val strokes = points
            .filter { it.drawingId == drawingId }
            .groupBy { it.strokeId }

        // Loop through each stroke and its points, preparing each point's position, color, and
        // half-size for drawing.
        strokes.values.forEach { stroke ->
            stroke.forEachIndexed { index, point ->
                val position = Offset(point.x, point.y)
                val color = Color(point.color)
                val halfSize = point.size / 2f

                when (point.brush) {
                    BrushType.LINE -> {
                        drawCircle(color, halfSize, position)

                        if (index > 0) {
                            val previous = stroke[index - 1]

                            drawLine(
                                color = color,
                                start = Offset(previous.x, previous.y),
                                end = position,
                                strokeWidth = point.size
                            )
                        }
                    }

                    BrushType.CIRCLE -> {
                        drawCircle(color, halfSize, position)
                    }

                    BrushType.RECTANGLE -> {
                        drawRect(
                            color = color,
                            topLeft = Offset(
                                point.x - halfSize,
                                point.y - halfSize
                            ),
                            size = Size(point.size, point.size)
                        )
                    }

                    BrushType.TRIANGLE -> {
                        // I'm not sure how to implement triangle brush
                        }
                    }
                }
            }
        }
    }
