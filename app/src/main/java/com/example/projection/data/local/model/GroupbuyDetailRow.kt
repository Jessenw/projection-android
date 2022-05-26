package com.example.projection.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projection.data.remote.model.ProjectDetail

@Entity(tableName = "groupbuy_detail")
data class GroupbuyDetailRow(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String
)

fun GroupbuyDetailRow.toProjectDetail(): ProjectDetail {
    with(this) {
        return ProjectDetail(id, title, content)
    }
}
