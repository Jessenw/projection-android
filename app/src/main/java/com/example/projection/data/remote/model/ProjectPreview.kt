package com.example.projection.data.remote.model

import com.example.projection.data.local.model.ProjectPreviewRow

data class ProjectPreview(
    val id: String,
    val title: String,
    val author: String,
    val saved: Boolean
)

fun ProjectPreview.toRow(): ProjectPreviewRow {
    with(this) {
        return ProjectPreviewRow(
            id = id.toInt(),
            title = title,
            author = author,
            saved = saved
        )
    }
}
