package com.example.drawingapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.drawingapp.DrawingViewModel

@Composable
fun CanvasScreen(myNavController : NavHostController, drawingVM: DrawingViewModel){
    val points by drawingVM.points.collectAsState()
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Canvas Screen")

        Button(onClick = {myNavController.navigate("PenScreen")}){
            Text("Pen")
        }

    }
}

