package com.example.drawingapp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.drawingapp.view.CanvasScreen
import com.example.drawingapp.view.PenScreen
import com.example.drawingapp.view.SplashScreen
import com.example.drawingapp.viewmodel.DrawingViewModel

@Composable
fun MyAppNav(
    myNavController: NavHostController,
    drawingVM: DrawingViewModel,
    startDestination: String = "splashScreen"
) {
    NavHost(
        navController = myNavController,
        startDestination = startDestination
    ) {
        composable("splashScreen") {
            SplashScreen(myNavController, drawingVM)
        }

        composable("canvasScreen/{drawingId}") { entry ->
            val drawingId = entry.arguments
                ?.getString("drawingId")
                ?.toIntOrNull()

            if (drawingId != null) {
                CanvasScreen(myNavController, drawingVM, drawingId)
            } else {
                Text("Missing or invalid drawing ID")
            }
        }

        composable("penScreen") {
            PenScreen(myNavController, drawingVM)
        }
    }
}