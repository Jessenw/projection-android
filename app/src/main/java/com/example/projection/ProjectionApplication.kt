package com.example.projection

import android.app.Application
import com.example.projection.data.persistence.projectpreview.ProjectionDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ProjectionApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { ProjectionDatabase.getDatabase(this, applicationScope) }
}