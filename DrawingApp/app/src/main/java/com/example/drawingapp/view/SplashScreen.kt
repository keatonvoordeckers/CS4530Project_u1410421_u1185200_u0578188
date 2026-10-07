package com.example.drawingapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.drawingapp.viewmodel.DrawingViewModel
import kotlinx.coroutines.delay
import kotlin.concurrent.timer
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SplashScreen(myNavController : NavHostController, drawingVM : DrawingViewModel) {

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Splash Screen")

    }

    var secondsLeft by remember { mutableStateOf(3) }

    // Referenced from slide 16 in Lecture 8
    LaunchedEffect(Unit) {
        while (secondsLeft > 0) {
            delay(1000.milliseconds) // Wait 1 second
            secondsLeft--
        }
        // When timer finishes, navigate to canvas screen
        drawingVM.addDrawing("Untitled") { id ->
            myNavController.navigate("canvasScreen/$id")
        }
    }
}

