package com.example.projection.data.persistence.projectpreview

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProjectPreviewRow::class], version = 1)
abstract class ProjectionDatabase : RoomDatabase() {
    abstract val projectPreviews: ProjectPreviewDao
}
