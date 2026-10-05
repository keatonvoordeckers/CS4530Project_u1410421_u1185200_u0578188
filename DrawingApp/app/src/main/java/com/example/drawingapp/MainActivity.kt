package com.example.drawingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.drawingapp.ui.theme.DrawingAppTheme
import com.example.drawingapp.viewmodel.DrawingProvider
import com.example.drawingapp.viewmodel.DrawingViewModel

class MainActivity : ComponentActivity() {
    private val drawingVM: DrawingViewModel by viewModels { DrawingProvider.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DrawingAppTheme {
                val navCon = rememberNavController()

                MyAppNav(
                    myNavController = navCon,
                    drawingVM = drawingVM,
                    startDestination = "splashScreen"
                )
            }
        }
    }
}