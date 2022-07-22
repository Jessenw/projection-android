package com.example.projection.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projection.data.local.dao.*
import com.example.projection.data.local.model.GroupbuyDetailRow
import com.example.projection.data.local.model.GroupbuyPreviewRow
import com.example.projection.data.local.model.InterestCheckPreviewRow
import com.example.projection.data.local.model.UserConfigurationRow

@Database(
    entities = [
        GroupbuyPreviewRow::class,
        InterestCheckPreviewRow::class,
        GroupbuyDetailRow::class,
        UserConfigurationRow::class
    ],
    version = 1
)
abstract class ProjectionDatabase : RoomDatabase() {
    abstract fun groupbuyIndexDao(): GroupbuyIndexLocalDataSource
    abstract fun interestCheckIndexDao(): InterestCheckIndexLocalDataSource
    abstract fun savedIndexDao(): SavedIndexLocalDataSource
    abstract fun groupbuyDetailDao(): GroupbuyDetailLocalDataSource
    abstract fun userConfigurationDao(): UserConfigurationLocalDataSource
}
