package com.example.projection.data.local.dao

import androidx.room.*
import com.example.projection.data.local.model.GroupbuyDetailRow
import com.example.projection.view.screen.ProjectDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupbuyDetailLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(project: GroupbuyDetailRow)

    @Query("""
        SELECT groupbuy_detail.id, groupbuy_detail.content 
        FROM groupbuy_detail 
        WHERE groupbuy_detail.id = {projectId}
        """)
    fun getProject(projectId: String): Flow<ProjectDetail>
}
