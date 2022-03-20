package com.example.projection.data.persistence.projectpreview

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projection.data.model.ProjectPreview

@Entity(tableName = "project_preview")
data class ProjectPreviewRow(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "saved") val saved: Boolean
) {
    object ModelMapper {
        fun from(preview: ProjectPreview): ProjectPreviewRow {
            val row = ProjectPreviewRow(
                preview.id.toFloat().toInt(),
                preview.title,
                preview.author,
                preview.saved
            )
            Log.d("ModelMapper", "${row.toString()}")

            return row
        }
    }
}
