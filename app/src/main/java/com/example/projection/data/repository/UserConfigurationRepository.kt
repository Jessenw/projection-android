package com.example.projection.data.repository

import com.example.projection.data.local.dao.UserConfigurationLocalDataSource
import com.example.projection.data.local.model.UserConfigurationRow
import com.example.projection.data.local.model.toUserConfiguration
import com.example.projection.data.remote.model.UserConfiguration
import com.example.projection.view.ui.theme.Palette
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface UserConfigurationRepository {
    fun getLatestUserConfiguration(): Flow<UserConfiguration>

    suspend fun updateTheme(theme: UserConfiguration)
}

class UserConfigurationRepositoryImpl @Inject constructor(
    private val localDataSource: UserConfigurationLocalDataSource
) : UserConfigurationRepository {

    override fun getLatestUserConfiguration(): Flow<UserConfiguration> {
        return flow {
            localDataSource.getUserConfiguration("1001")
                .flowOn(Dispatchers.IO)
                .collect { row: UserConfigurationRow ->
                    /* TODO: This is likely null, since userConfiguration will
                     * be determined by the authenticated user. For now, if there's
                     * no configuration row, just create one
                     */
                    if (row == null) {
                        localDataSource.insert(UserConfigurationRow("1001", Palette.EightOhOhEight.nameId))
                    } else {
                        emit(row.toUserConfiguration())
                    }
                }
        }
    }

    override suspend fun updateTheme(theme: UserConfiguration) {
        localDataSource.updateUserConfiguration(theme)
    }
}
