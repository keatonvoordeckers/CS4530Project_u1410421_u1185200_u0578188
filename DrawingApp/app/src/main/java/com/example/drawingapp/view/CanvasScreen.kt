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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.drawingapp.viewmodel.DrawingViewModel

@Composable
fun CanvasScreen(myNavController : NavHostController, drawingVM: DrawingViewModel, drawingID: Int){
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Canvas Screen")

        Button(onClick = {myNavController.navigate("PenScreen")}){
            Text("Pen")
        }

        DrawingCanvas(drawingVM, drawingID)
    }
}

@Composable
fun DrawingCanvas(viewModel: DrawingViewModel, id: Int){
    val points by viewModel.points.collectAsState()

    Canvas(modifier = Modifier
        .size(300.dp)
        .background(Color.LightGray)
        ){
    }
}

// Ripped directly from DrawingDemoV2
@Composable
fun DrawingCanvasPoints() {
    var strokes by remember { mutableStateOf(listOf<List<Offset>>()) }
    var currentStroke by remember { mutableStateOf(listOf<Offset>()) }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            //We capture touch input with
            // pointerInput and detectDragGestures.
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        currentStroke = listOf(offset)
                        //if you update current stroke live here not on DragEnd,
                        // then you do not need a second loop
                        strokes = strokes + listOf(currentStroke)
                    },
                    onDrag = { change, x ->
                        change.consume()
                        currentStroke = currentStroke + change.position
                        //if you update current stroke live here not on DragEnd,
                        // then you do not need a second loop
                        strokes = strokes.dropLast(1) + listOf(currentStroke)
                    },
                    onDragEnd = {
                        //strokes = strokes + listOf(currentStroke)
                        currentStroke = emptyList()
                    }
                )
            }
    ) {
        // Draw all completed strokes
        strokes.forEach { stroke ->
            for (i in 0 until stroke.size - 1) {
                drawLine(
                    color = Color.Red,
                    start = stroke[i],
                    end = stroke[i + 1],
                    strokeWidth = 8f
                )
            }
        }
    }
}