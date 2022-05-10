package com.example.projection.data.local.model

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projection.data.remote.model.ProjectPreview

@Entity(tableName = "groupbuy")
data class ProjectPreviewRow(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "saved") val saved: Boolean
)

fun List<ProjectPreviewRow>.toProjectPreviewList(): List<ProjectPreview> {
    return this.map { it.toProjectPreview() }
}

fun ProjectPreviewRow.toProjectPreview(): ProjectPreview {
    with(this) {
        return ProjectPreview(
            id = id.toString(),
            title = title,
            author = author,
            saved = saved
        )
    }
}
