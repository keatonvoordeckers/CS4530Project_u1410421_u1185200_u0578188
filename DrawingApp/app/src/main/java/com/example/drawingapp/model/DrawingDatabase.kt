package com.example.drawingapp.model

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Database(entities = [DrawingData::class, PointData::class], version = 1, exportSchema = false)
abstract class DrawingDatabase: RoomDatabase() {
    abstract fun drawingDao(): DrawingDao
}

@Dao
interface DrawingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDrawing(drawing: DrawingData): Long

    @Insert
    suspend fun addPoints(points: List<PointData>)

    @Delete
    suspend fun deleteDrawing(drawing: DrawingData)

    @Delete
    suspend fun deletePoints(points: List<PointData>)

    @Query("SELECT * FROM drawings ORDER BY updatedAt DESC")
    fun getAllDrawings(): Flow<List<DrawingData>>

    @Query("SELECT * FROM points WHERE drawingId = :drawingId ORDER BY id")
    suspend fun getPoints(drawingId: Int): List<PointData>
}