package com.example.projection.data.remote.model

import com.example.projection.data.local.model.ProjectPreviewRow

data class ProjectsResponse(
    val projects: List<ProjectPreview>
)

fun ProjectsResponse.toRow(): List<ProjectPreviewRow> {
    return this.projects.map { it.toRow() }
}
