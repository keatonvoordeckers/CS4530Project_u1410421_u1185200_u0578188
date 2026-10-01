package com.example.drawingapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun SplashScreen(myNavController : NavHostController){

    Column(modifier = Modifier.fillMaxWidth()){
        Text("Splash Screen")
    }
}