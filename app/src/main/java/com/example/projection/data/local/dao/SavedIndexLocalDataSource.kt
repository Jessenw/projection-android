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
        SELECT groupbuy.id, groupbuy.title, groupbuy.author, groupbuy.type, groupbuy.saved AS saved
        FROM groupbuy WHERE saved=1
        UNION
        SELECT interest_check.id, interest_check.title, interest_check.author, interest_check.type, interest_check.saved AS saved
        FROM interest_check WHERE saved=1"""
    )
    fun getAll(): Flow<List<ProjectPreview>>

    @Update(GroupbuyPreviewRow::class)
    suspend fun updateGroupbuySaved(saved: ProjectPreviewSaved)

    @Update(InterestCheckPreviewRow::class)
    suspend fun updateInterestCheckSaved(saved: ProjectPreviewSaved)
}

