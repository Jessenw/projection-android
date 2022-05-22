package com.example.projection.data.repository

import com.example.projection.data.local.dao.SavedIndexLocalDataSource
import com.example.projection.data.local.model.ProjectPreviewSaved
import com.example.projection.data.remote.model.ProjectPreview
import com.example.projection.data.remote.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface SavedIndexRepository {
    suspend fun getLatestSaved(): Flow<Result<List<ProjectPreview>>>
    suspend fun updateSaved(saved: ProjectPreviewSaved)
}

class SavedIndexRepositoryImpl @Inject constructor(
    private val localDataSource: SavedIndexLocalDataSource,
) : SavedIndexRepository {

    override suspend fun getLatestSaved(): Flow<Result<List<ProjectPreview>>> {
        return flow {
            localDataSource.getAll()
                .flowOn(Dispatchers.IO)
                .collect {
                    emit(Result.success(it))
                }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun updateSaved(saved: ProjectPreviewSaved) {
        if (saved.type == "groupbuy") {
            localDataSource.updateGroupbuySaved(saved)
        } else if (saved.type == "interest_check") {
            localDataSource.updateInterestCheckSaved(saved)
        }
    }
}
