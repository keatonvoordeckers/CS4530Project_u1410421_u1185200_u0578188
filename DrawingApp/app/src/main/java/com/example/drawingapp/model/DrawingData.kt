package com.example.drawingapp.model
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.lang.System.currentTimeMillis

enum class BrushType {
    LINE, CIRCLE, RECTANGLE, TRIANGLE
}
@Entity(tableName = "drawings")
data class DrawingData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String = "Untitled",
    val updatedAt: Long = currentTimeMillis(),
)
// I looked into how to set up a foreign key in android's room from here
// https://medium.com/knowing-android/select-insert-indexes-and-foreign-keys-on-room-migrations-2a0dc556efd3
// i know it's not required until part 2 but it seemed easier to add it now rather than later
@Entity(
    tableName = "points",
    foreignKeys = [ForeignKey(
        entity = DrawingData::class,
        parentColumns = ["id"],
        childColumns = ["drawingId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class PointData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    // this class is a rip off of Offset from DrawingDemoV2, but with more support for drawing/data persistence
    // https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:compose/ui/ui-geometry/src/commonMain/kotlin/androidx/compose/ui/geometry/Offset.kt?q=file:androidx%2Fcompose%2Fui%2Fgeometry%2FOffset.kt%20class:androidx.compose.ui.geometry.Offset
    val drawingId: Int,
    val strokeId: Int, // if we use this we can have an undo button, but maybe not necessary
    val x: Float,
    val y: Float,
    val color: Int,
    val size: Float,
    val brush: BrushType
)