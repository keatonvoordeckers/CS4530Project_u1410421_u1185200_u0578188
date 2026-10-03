package com.example.drawingapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.drawingapp.BrushType
import com.example.drawingapp.DrawingViewModel

@Composable
fun PenScreen(myNavController: NavHostController, drawingVM: DrawingViewModel) {
    val size by drawingVM.penSize.collectAsState()
    val color by drawingVM.penColor.collectAsState()
    val brush by drawingVM.penBrush.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(16.dp))
        Text("Pen Screen")
        Spacer(modifier = Modifier.padding(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { if (size > 5.0f) drawingVM.setPenSize(size - 5.0f) }) {
                Text("-")
            }
            Text("Size: ${size.toInt()}", modifier = Modifier.padding(8.dp))
            Button(onClick = { if (size < 100.0f) drawingVM.setPenSize(size + 5.0f) }) {
                Text("+")
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Color:")
            Surface(
                color = color,
                modifier = Modifier.padding(8.dp).clip(RoundedCornerShape(4.dp))
            ) {
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }
        Row {
            Button(onClick = { drawingVM.setPenColor(Color.Black) }) { Text("Black") }
            Button(onClick = { drawingVM.setPenColor(Color.Red) }) { Text("Red") }
            Button(onClick = { drawingVM.setPenColor(Color.Green) }) { Text("Green") }
        }
        Row {
            Button(onClick = { drawingVM.setPenColor(Color.Blue) }) { Text("Blue") }
            Button(onClick = { drawingVM.setPenColor(Color.Yellow) }) { Text("Yellow") }
            Button(onClick = { drawingVM.setPenColor(Color.Magenta) }) { Text("Magenta") }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text("Brush: ${brush.name}")
        Row {
            Button(onClick = { drawingVM.setPenBrush(BrushType.LINE) }) { Text("Line") }
            Button(onClick = { drawingVM.setPenBrush(BrushType.CIRCLE) }) { Text("Circle") }
        }
        Row {
            Button(onClick = { drawingVM.setPenBrush(BrushType.RECTANGLE) }) { Text("Rectangle") }
            Button(onClick = { drawingVM.setPenBrush(BrushType.TRIANGLE) }) { Text("Triangle") }
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = { myNavController.navigate("canvasScreen") }) {
            Text("Canvas")
        }
    }
}