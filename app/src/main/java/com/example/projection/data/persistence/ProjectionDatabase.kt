package com.example.projection.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ProjectPreviewRow::class], version = 1)
abstract class ProjectionDatabase : RoomDatabase() {

    abstract fun projectPreviewDao(): ProjectPreviewDao

    companion object {
        @Volatile
        private var INSTANCE: ProjectionDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ProjectionDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProjectionDatabase::class.java,
                    "projection_database"
                )
                    // TODO: Wipes database, need to set-up migration
                    .fallbackToDestructiveMigration()
                    .addCallback(ProjectionDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class ProjectionDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.projectPreviewDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(projectPreviewDao: ProjectPreviewDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            val project = ProjectPreviewRow(
                id = 0,
                title = "Test project",
                author = "Jesse W"
            )
            projectPreviewDao.insert(project)
        }
    }
}