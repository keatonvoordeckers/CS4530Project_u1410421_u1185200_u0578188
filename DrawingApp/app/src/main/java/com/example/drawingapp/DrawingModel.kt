package com.example.drawingapp

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DrawingViewModel(private val drawingRepository: DrawingRepository) : ViewModel() {
    val penSize = MutableStateFlow(10.0f)
    val penColor = MutableStateFlow(Color.Black)
    val penBrush = MutableStateFlow(BrushType.LINE)
    val allDrawings: StateFlow<List<DrawingData>> = drawingRepository.allDrawings.stateIn(
        scope = drawingRepository.scope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = listOf()
    )
    val points = MutableStateFlow<List<PointData>>(emptyList())

    fun addDrawing(title: String) {
        drawingRepository.addDrawing(title)
    }

    fun deleteDrawing(drawing: DrawingData) {
        drawingRepository.deleteDrawing(drawing)
    }

    fun loadPoints(drawingId: Int) {
        drawingRepository.scope.launch {
            points.value = drawingRepository.getPoints(drawingId)
        }
    }

    fun addPoints(newPoints: List<PointData>) {
        points.value += newPoints
        drawingRepository.addPoints(newPoints)
    }

    fun deletePoints(oldPoints: List<PointData>) {
        points.value -= oldPoints.toSet()
        drawingRepository.deletePoints(oldPoints)
    }

    fun setPenColor(color: Color) {
        penColor.value = color
    }

    fun setPenSize(size: Float) {
        penSize.value = size
    }

    fun setPenBrush(brush: BrushType) {
        penBrush.value = brush
    }
}

object DrawingProvider {
    val Factory = viewModelFactory {
        initializer {
            DrawingViewModel((this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DrawingApplication).drawingRepository
            )
        }
    }
}