package com.example.projection

import android.app.Application
import androidx.room.Room
import com.example.projection.data.persistence.projectpreview.ProjectionDatabase
import com.example.projection.data.repository.ProjectionRepository

class ProjectionApp : Application() {

    private val database by lazy {
        Room
            .databaseBuilder(this, ProjectionDatabase::class.java, "database.db")
            .build()
    }

    val projectPreviewRepository by lazy { ProjectionRepository(database) }
}
