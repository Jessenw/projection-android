package com.example.projection.data.repository

import com.dropbox.android.external.store4.*
import com.example.projection.data.local.dao.GroupbuyDetailLocalDataSource
import com.example.projection.data.local.model.GroupbuyDetailRow
import com.example.projection.data.local.model.toProjectDetail
import com.example.projection.data.remote.endpoint.GroupbuyDetailRemoteDataSource
import com.example.projection.data.remote.model.ProjectDetail
import com.example.projection.data.remote.model.toGroupbuyRow
import com.example.projection.data.remote.model.Result
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface GroupbuyDetailRepository {
    suspend fun getGroupbuy(refresh: Boolean): Flow<Result<ProjectDetail>>
}

@AssistedFactory
interface GroupbuyDetailRepositoryFactory {
    fun create(projectId: String): GroupbuyDetailRepositoryImpl
}

class GroupbuyDetailRepositoryImpl @AssistedInject constructor(
    private val localDataSource: GroupbuyDetailLocalDataSource,
    private val remoteDataSource: GroupbuyDetailRemoteDataSource,
    @Assisted private val projectId: String
) : GroupbuyDetailRepository {

    private val store: Store<String, GroupbuyDetailRow> = StoreBuilder.from(
        fetcher = Fetcher.ofFlow { remoteDataSource.getGroupbuy(projectId) },
        sourceOfTruth = SourceOfTruth.Companion.of(
            reader = { localDataSource.getProject(projectId) },
            writer = { _: String, input: ProjectDetail ->
                localDataSource.insert(input.toGroupbuyRow())
            },
            delete = { localDataSource.deleteAll() },
            deleteAll = { localDataSource.deleteAll() },
        )
    ).build()

    override suspend fun getGroupbuy(refresh: Boolean): Flow<Result<ProjectDetail>> {
        return flow<Result<ProjectDetail>> {
            store.stream(StoreRequest.cached("latest_groupbuy_detail", refresh))
                .flowOn(Dispatchers.IO)
                .collect { response: StoreResponse<GroupbuyDetailRow> ->
                    when(response) {
                        is StoreResponse.Loading -> {
                            println("[Store 4] Loading from $response")
                            emit(Result.loading())
                        }
                        is StoreResponse.Error -> {
                            println("[Store 4] Error from $response")
                            emit(Result.error())
                        }
                        is StoreResponse.Data -> {
                            val data = response.value.toProjectDetail()
                            println("[Store 4] Data: $response")
                            emit(Result.success(data))
                        }
                    }
                }
        }.flowOn(Dispatchers.IO)
    }
}
