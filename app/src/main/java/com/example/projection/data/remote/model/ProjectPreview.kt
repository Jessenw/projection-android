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
    GroupbuyPreviewRow(id, title, author, type ?: "groupbuy", saved)

fun ProjectPreview.toInterestCheckRow(): InterestCheckPreviewRow =
    InterestCheckPreviewRow(id, title, author, type ?: "interest_check", saved)


