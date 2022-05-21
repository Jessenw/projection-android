package com.example.projection.data.remote.model

import com.example.projection.data.local.model.GroupbuyPreviewRow
import com.example.projection.data.local.model.toGroupbuyRow

data class ProjectsResponse(
    val projects: List<ProjectPreview>
)

fun ProjectsResponse.toGroupbuyRow(): List<GroupbuyPreviewRow> {
    return this.projects.map { it.toGroupbuyRow() }
}
