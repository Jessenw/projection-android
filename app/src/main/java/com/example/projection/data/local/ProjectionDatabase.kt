package com.example.projection.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projection.data.local.dao.GroupbuyLocalDataSource
import com.example.projection.data.local.model.ProjectPreviewRow

@Database(entities = [ProjectPreviewRow::class], version = 1)
abstract class ProjectionDatabase : RoomDatabase() {
    abstract fun groupbuyDao(): GroupbuyLocalDataSource
}
