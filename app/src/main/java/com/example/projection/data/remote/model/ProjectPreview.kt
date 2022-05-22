package com.example.projection.data.remote.model

import com.example.projection.data.local.model.GroupbuyPreviewRow
import com.example.projection.data.local.model.InterestCheckPreviewRow

/** A generic representation of a project preview */
data class ProjectPreview(
    val id: String,
    val title: String,
    val author: String,
    val type: String?,
    val saved: Boolean
)

fun ProjectPreview.toGroupbuyRow(): GroupbuyPreviewRow =
    GroupbuyPreviewRow(
        id = this.id,
        title = this.title,
        author = this.author,
        type = this.type ?: "groupbuy",
        saved = this.saved
    )

fun ProjectPreview.toInterestCheckRow(): InterestCheckPreviewRow =
    InterestCheckPreviewRow(
        id = this.id,
        title = this.title,
        author = this.author,
        type = this.type ?: "interest_check",
        saved = this.saved
    )
