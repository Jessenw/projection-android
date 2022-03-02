package com.example.projection

import android.app.Application
import com.example.projection.data.persistence.ProjectionDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ProjectionApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    fun getDatabase() = ProjectionDatabase.getDatabase(this, applicationScope)
}
