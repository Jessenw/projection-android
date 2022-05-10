package com.example.projection.data.local.dao

import androidx.room.*
import com.example.projection.data.local.model.ProjectPreviewRow
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupbuyLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(projects: List<ProjectPreviewRow>)

    @Query("SELECT * FROM groupbuy")
    fun getAll(): Flow<List<ProjectPreviewRow>?>

    @Query("DELETE FROM groupbuy")
    suspend fun deleteAll()

    @Transaction
    suspend fun update(projects: List<ProjectPreviewRow>) {
        deleteAll()
        insert(projects)
    }
}
