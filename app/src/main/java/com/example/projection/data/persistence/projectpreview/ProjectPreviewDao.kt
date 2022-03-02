package com.example.projection.data.persistence.projectpreview

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface ProjectPreviewDao {
    @Query("SELECT * FROM ProjectPreviewRow")
    fun getAll(): Flowable<List<ProjectPreviewRow>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(project: ProjectPreviewRow): Completable

    @Delete
    fun delete(project: ProjectPreviewRow)
}