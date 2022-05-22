package com.example.projection.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.projection.data.local.model.InterestCheckPreviewRow
import kotlinx.coroutines.flow.Flow

@Dao
interface InterestCheckLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(projects: List<InterestCheckPreviewRow>)

    @Query("SELECT * FROM interest_check")
    fun getAll(): Flow<List<InterestCheckPreviewRow>?>

    @Query("DELETE FROM interest_check")
    suspend fun deleteAll()
}
