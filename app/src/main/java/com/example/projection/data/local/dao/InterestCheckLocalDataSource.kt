package com.example.projection.data.local.dao

import androidx.room.*
import com.example.projection.data.local.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface InterestCheckLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(projects: List<InterestCheckPreviewRow>)

    @Query("SELECT * FROM interest_check")
    fun getAll(): Flow<List<InterestCheckPreviewRow>?>

    @Query("DELETE FROM interest_check")
    suspend fun deleteAll()

    @Update(InterestCheckPreviewRow::class)
    suspend fun updateProjectSaved(saved: ProjectPreviewSaved)
}
