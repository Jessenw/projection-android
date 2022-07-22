package com.example.projection.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projection.data.remote.model.ProjectDetail

@Entity(tableName = "groupbuy_detail")
data class GroupbuyDetailRow(
    @PrimaryKey @ColumnInfo val id: String,
    @ColumnInfo val title: String,
    @ColumnInfo val content: String
)

fun GroupbuyDetailRow.toProjectDetail() =
    ProjectDetail(id, title, content)
