package com.example.drawingapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.drawingapp.view.CanvasScreen
import com.example.drawingapp.view.PenScreen
import com.example.drawingapp.view.SplashScreen

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
            SplashScreen(myNavController)
        }

        composable("canvasScreen") {
            CanvasScreen(myNavController, drawingVM)
        }

        composable("penScreen") {
            PenScreen(myNavController, drawingVM)
        }
    }
}