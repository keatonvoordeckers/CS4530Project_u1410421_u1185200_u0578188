package com.example.drawingapp

import com.example.drawingapp.model.BrushType
import com.example.drawingapp.model.DrawingData
import com.example.drawingapp.model.PointData

import org.junit.Test

import org.junit.Assert.*

/**
 * Unit tests for the model of the Drawing Application. Using JUnit tests.
 * Used ChatGPT to generate some unit tests.
 * Updated: 10/7/2026
 */
class DrawingAppUnitTests {
    // DrawingData Tests
    @Test
    fun drawingDataDefaultValues() {
        val drawing = DrawingData()

        assertEquals(0, drawing.id)
        assertEquals("Untitled", drawing.title)
    }

    @Test
    fun drawingDataCustomValues() {
        val drawing = DrawingData(
            id = 5,
            title = "My Drawing",
            updatedAt = 123456789L
        )

        assertEquals(5, drawing.id)
        assertEquals("My Drawing", drawing.title)
        assertEquals(123456789L, drawing.updatedAt)
    }

    @Test
    fun drawingDataUpdatedAtIsCurrentTime() {
        val before = System.currentTimeMillis()

        val drawing = DrawingData()

        val after = System.currentTimeMillis()

        assert(drawing.updatedAt in before..after)
    }

    @Test
    fun pointDataStoresValues() {
        val point = PointData(
            id = 1,
            drawingId = 10,
            strokeId = 2,
            x = 100.5f,
            y = 200.5f,
            color = 0xFFFF0000.toInt(),
            size = 5.0f,
            brush = BrushType.CIRCLE
        )

        assertEquals(1, point.id)
        assertEquals(10, point.drawingId)
        assertEquals(2, point.strokeId)
        assertEquals(100.5f, point.x, 0.001f)
        assertEquals(200.5f, point.y, 0.001f)
        assertEquals(0xFFFF0000.toInt(), point.color)
        assertEquals(5.0f, point.size, 0.001f)
        assertEquals(BrushType.CIRCLE, point.brush)
    }

    @Test
    fun brushTypeContainsAllBrushes() {
        val brushes = BrushType.entries

        assertEquals(4, brushes.size)
        assertEquals(BrushType.LINE, brushes[0])
        assertEquals(BrushType.CIRCLE, brushes[1])
        assertEquals(BrushType.RECTANGLE, brushes[2])
        assertEquals(BrushType.TRIANGLE, brushes[3])
    }

    // TODO: add tests for DrawingDatabase

}