package com.example.projection.data.remote.model

import com.example.projection.data.local.model.GroupbuyPreviewRow
import com.example.projection.data.local.model.InterestCheckPreviewRow
import com.example.projection.data.local.model.toGroupbuyRow
import com.example.projection.data.local.model.toInterestCheckRow

data class ProjectsResponse(
    val projects: List<ProjectPreview>
)

fun ProjectsResponse.toGroupbuyRow(): List<GroupbuyPreviewRow> =
    this.projects.map { it.toGroupbuyRow() }

fun ProjectsResponse.toInterestCheckRow(): List<InterestCheckPreviewRow> =
    this.projects.map { it.toInterestCheckRow() }
