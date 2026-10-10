package com.example.drawingapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.drawingapp.R
import com.example.drawingapp.viewmodel.DrawingViewModel
import kotlinx.coroutines.delay
import kotlin.concurrent.timer
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SplashScreen(myNavController : NavHostController, drawingVM : DrawingViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("splash_screen"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.drawing_app_logo),
            contentDescription = "app logo",
            modifier = Modifier
                .size(200.dp)
        )
    }

    // Referenced from slide 16 in Lecture 8
    LaunchedEffect(Unit) {
        delay(3000.milliseconds)

        // When timer finishes, navigate to canvas screen
        drawingVM.addDrawing("Untitled") { id ->
            myNavController.navigate("canvasScreen/$id")
        }
    }
}

