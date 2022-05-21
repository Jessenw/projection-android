package com.example.projection.data.repository

import com.dropbox.android.external.store4.*
import com.example.projection.data.local.dao.GroupbuyLocalDataSource
import com.example.projection.data.local.model.GroupbuyPreviewRow
import com.example.projection.data.local.model.toProjectPreviewList
import com.example.projection.data.remote.groupbuy.GroupbuyRemoteDataSource
import com.example.projection.data.remote.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GroupbuyRepository {
    suspend fun getLatestGroupbuys(refresh: Boolean): Flow<Result<List<ProjectPreview>>>
}

class GroupbuyRepositoryImpl @Inject constructor(
    private val localDataSource: GroupbuyLocalDataSource,
    private val remoteDataSource: GroupbuyRemoteDataSource,
) : GroupbuyRepository {

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
                            println("[Store 4] Data from ${response.origin} with ${response.value.size} elements")
                            println("[Store 4] Data: $response")
                            emit(Result.success(data))
                        }
                        is StoreResponse.NoNewData -> emit(Result.success(emptyList()))
                    }
                }
        }.flowOn(Dispatchers.IO)
    }
}
