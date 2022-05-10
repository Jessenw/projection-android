package com.example.projection

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ProjectionApp : Application() { }

//    private val store = StoreBuilder
//        .from(Fetcher.ofFlow {  })
//
//    private val database by lazy {
//        Room
//            .databaseBuilder(this, ProjectionDatabase::class.java, "database.db")
//            .build()
//    }
//
//    val projectPreviewRepository by lazy { ProjectionRepository(database) }
//}
