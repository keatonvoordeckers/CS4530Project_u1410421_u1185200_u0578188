package com.example.drawingapp.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DrawingRepository(val scope: CoroutineScope, private val drawingDao: DrawingDao) {
    val allDrawings: Flow<List<DrawingData>> = drawingDao.getAllDrawings()
    suspend fun getPoints(drawingId: Int): List<PointData> = drawingDao.getPoints(drawingId)
    suspend fun addDrawing(title: String): Int {
        val drawing = DrawingData(title = title)
        return drawingDao.addDrawing(drawing).toInt()
    }

    fun deleteDrawing(drawing: DrawingData){
        scope.launch {
            drawingDao.deleteDrawing(drawing)
        }
    }

    fun addPoints(points: List<PointData>) {
        scope.launch {
            drawingDao.addPoints(points)
        }
    }

    fun deletePoints(points: List<PointData>) {
        scope.launch {
            drawingDao.deletePoints(points)
        }
    }
}