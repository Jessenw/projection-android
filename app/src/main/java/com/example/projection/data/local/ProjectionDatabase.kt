package com.example.projection.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projection.data.local.dao.GroupbuyLocalDataSource
import com.example.projection.data.local.model.GroupbuyPreviewRow

@Database(entities = [GroupbuyPreviewRow::class], version = 1)
abstract class ProjectionDatabase : RoomDatabase() {
    abstract fun groupbuyDao(): GroupbuyLocalDataSource
}
