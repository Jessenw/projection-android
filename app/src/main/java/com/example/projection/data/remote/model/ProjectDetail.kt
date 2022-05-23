package com.example.projection.data.remote.model

import com.example.projection.data.local.model.GroupbuyDetailRow

data class ProjectDetail(
    val id: String,
    val content: String
)

fun ProjectDetail.toGroupbuyRow(): GroupbuyDetailRow =
    GroupbuyDetailRow(id, content)
