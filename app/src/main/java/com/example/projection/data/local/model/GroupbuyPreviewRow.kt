package com.example.projection.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projection.data.remote.model.ProjectPreview

@Entity("groupbuy")
data class GroupbuyPreviewRow(
    @PrimaryKey @ColumnInfo val id: String,
    @ColumnInfo val title: String,
    @ColumnInfo val author: String,
    @ColumnInfo(defaultValue = "groupbuy") val type: String,
    @ColumnInfo val saved: Boolean
)

fun List<GroupbuyPreviewRow>.toProjectPreviewList() =
    this.map { it.toProjectPreview() }

fun GroupbuyPreviewRow.toProjectPreview() =
    ProjectPreview(
        id = id,
        title = title,
        author = author,
        type = type,
        saved = saved
    )
