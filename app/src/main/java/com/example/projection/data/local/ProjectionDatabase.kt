package com.example.projection.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projection.data.local.dao.GroupbuyIndexLocalDataSource
import com.example.projection.data.local.dao.InterestCheckIndexLocalDataSource
import com.example.projection.data.local.dao.SavedIndexLocalDataSource
import com.example.projection.data.local.model.GroupbuyPreviewRow
import com.example.projection.data.local.model.InterestCheckPreviewRow

@Database(entities = [GroupbuyPreviewRow::class, InterestCheckPreviewRow::class], version = 1)
abstract class ProjectionDatabase : RoomDatabase() {
    abstract fun groupbuyDao(): GroupbuyIndexLocalDataSource
    abstract fun interestCheckDao(): InterestCheckIndexLocalDataSource
    abstract fun savedIndexDao(): SavedIndexLocalDataSource
}
