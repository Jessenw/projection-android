package com.example.projection.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.projection.data.local.model.GroupbuyPreviewRow
import com.example.projection.data.local.model.InterestCheckPreviewRow
import com.example.projection.data.local.model.ProjectPreviewSaved
import com.example.projection.data.remote.model.ProjectPreview
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedIndexLocalDataSource {

    @Query("""
        SELECT * FROM groupbuy
        WHERE saved=1
        UNION
        SELECT * FROM interest_check
        WHERE saved=1
        """)
    fun getAll(): Flow<List<ProjectPreview>>

    @Update(GroupbuyPreviewRow::class)
    suspend fun updateGroupbuySaved(saved: ProjectPreviewSaved)

    @Update(InterestCheckPreviewRow::class)
    suspend fun updateInterestCheckSaved(saved: ProjectPreviewSaved)
}

