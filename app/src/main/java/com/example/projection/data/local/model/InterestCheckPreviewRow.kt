package com.example.projection.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projection.data.remote.model.ProjectPreview

@Entity("interest_check")
data class InterestCheckPreviewRow(
    @PrimaryKey @ColumnInfo val id: String,
    @ColumnInfo val title: String,
    @ColumnInfo val author: String,
    @ColumnInfo(defaultValue = "interest_check") val type: String,
    @ColumnInfo val saved: Boolean
)

fun List<InterestCheckPreviewRow>.toProjectPreviewList(): List<ProjectPreview> =
    this.map { it.toProjectPreview() }

fun InterestCheckPreviewRow.toProjectPreview() =
    ProjectPreview(
        id = id,
        title = title,
        author = author,
        type = type,
        saved = saved
    )
