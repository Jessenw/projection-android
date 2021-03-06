package com.example.projection.data.repository

import com.dropbox.android.external.store4.*
import com.example.projection.data.local.dao.GroupbuyIndexLocalDataSource
import com.example.projection.data.local.model.GroupbuyPreviewRow
import com.example.projection.data.local.model.ProjectPreviewSaved
import com.example.projection.data.local.model.toProjectPreviewList
import com.example.projection.data.remote.endpoint.GroupbuyIndexRemoteDataSource
import com.example.projection.data.remote.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GroupbuyIndexRepository {
    suspend fun getLatestGroupbuys(refresh: Boolean): Flow<Result<List<ProjectPreview>>>

    suspend fun updateSaved(saved: ProjectPreviewSaved)
}

class GroupbuyIndexRepositoryImpl @Inject constructor(
    private val localDataSource: GroupbuyIndexLocalDataSource,
    private val remoteDataSource: GroupbuyIndexRemoteDataSource,
) : GroupbuyIndexRepository {

    private val store: Store<String, List<GroupbuyPreviewRow>> = StoreBuilder.from(
        fetcher = Fetcher.ofFlow { remoteDataSource.getLatestGroupbuys() },
        sourceOfTruth = SourceOfTruth.Companion.of(
            reader = { localDataSource.getAll() },
            writer = { _: String, input: ProjectsResponse ->
                localDataSource.insert(input.toGroupbuyRow())
            },
            delete = { localDataSource.deleteAll() },
            deleteAll = { localDataSource.deleteAll() }
        )
    ).build()

    override suspend fun getLatestGroupbuys(refresh: Boolean): Flow<Result<List<ProjectPreview>>> {
        return flow<Result<List<ProjectPreview>>> {
            store.stream(StoreRequest.cached(key = "latest_groupbuy", refresh))
                .flowOn(Dispatchers.IO)
                .collect { response: StoreResponse<List<GroupbuyPreviewRow>> ->
                    when (response) {
                        is StoreResponse.Loading -> {
                            println("[Store 4] Loading from $response")
                            emit(Result.loading())
                        }
                        is StoreResponse.Error -> {
                            println("[Store 4] Error from $response")
                            emit(Result.error())
                        }
                        is StoreResponse.Data -> {
                            val data = response.value.toProjectPreviewList()
                            println("[Store 4] Data: $response")
                            emit(Result.success(data))
                        }
                        is StoreResponse.NoNewData -> emit(Result.success(emptyList()))
                    }
                }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun updateSaved(saved: ProjectPreviewSaved) {
        localDataSource.updateProjectSaved(saved)
    }
}
