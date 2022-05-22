package com.example.projection.data.local.dao

import androidx.room.*
import com.example.projection.data.local.model.GroupbuyPreviewRow
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupbuyLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(projects: List<GroupbuyPreviewRow>)

    @Query("SELECT * FROM groupbuy")
    fun getAll(): Flow<List<GroupbuyPreviewRow>?>

    @Query("DELETE FROM groupbuy")
    suspend fun deleteAll()
}
