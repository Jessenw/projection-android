package com.example.projection.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projection.data.remote.model.ProjectPreview

@Entity("groupbuy")
data class GroupbuyPreviewRow(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "saved") val saved: Boolean
)

fun List<GroupbuyPreviewRow>.toProjectPreviewList(): List<ProjectPreview> =
    this.map { it.toProjectPreview() }

fun GroupbuyPreviewRow.toProjectPreview(): ProjectPreview {
    with(this) {
        return ProjectPreview(
            id = id,
            title = title,
            author = author,
            saved = saved
        )
    }
}

fun ProjectPreview.toGroupbuyRow(): GroupbuyPreviewRow =
    GroupbuyPreviewRow(
        id = this.id,
        title = this.title,
        author = this.author,
        saved = this.saved
    )
