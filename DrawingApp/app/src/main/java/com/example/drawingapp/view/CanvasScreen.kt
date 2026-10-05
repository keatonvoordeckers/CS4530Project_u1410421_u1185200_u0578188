package com.example.drawingapp.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        .background(Color.LightGray)){
    }
}
