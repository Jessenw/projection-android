package com.example.projection.data.local.dao

import androidx.room.*
import com.example.projection.data.local.model.GroupbuyPreviewRow
import com.example.projection.data.local.model.ProjectPreviewSaved
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupbuyIndexLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(projects: List<GroupbuyPreviewRow>)

    @Query("SELECT * FROM groupbuy")
    fun getAll(): Flow<List<GroupbuyPreviewRow>?>

    @Query("DELETE FROM groupbuy")
    suspend fun deleteAll()

    @Update(GroupbuyPreviewRow::class)
    suspend fun updateProjectSaved(saved: ProjectPreviewSaved)
}
