package com.example.projection.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.projection.data.local.model.GroupbuyPreviewRow
import com.example.projection.data.local.model.ProjectPreviewPOJO
import com.example.projection.data.remote.model.ProjectPreview
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedIndexLocalDataSource {
    @Query(
        "SELECT groupbuy.id, groupbuy.title, groupbuy.author, groupbuy.saved AS saved FROM groupbuy WHERE saved=1 UNION SELECT interest_check.id, interest_check.title, interest_check.author, interest_check.saved AS saved FROM interest_check WHERE saved=1"
//        "SELECT groupbuy.id, groupbuy.title, groupbuy.author, groupbuy.saved AS gb_saved" +
//        "FROM groupbuy WHERE gb_saved=1" +
//        "UNION" +
//        "SELECT interest_check.id, interest_check.title, interest_check.author, interest_check.saved AS ic_saved" +
//        "FROM interest_check WHERE ic_saved=1"
    )
    fun getAll(): Flow<List<ProjectPreview>>
}

