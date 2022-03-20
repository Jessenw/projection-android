package com.example.projection.data.model

import com.example.projection.data.persistence.projectpreview.ProjectPreviewRow

data class ProjectPreview(
    val id: String, // TODO: This will be updated to an int
    val title: String,
    val author: String,
    val saved: Boolean
) {
    object RowMapper {
        fun from(row: ProjectPreviewRow) =
            ProjectPreview(row.id.toString(), row.title, row.author, row.saved)
    }
}
