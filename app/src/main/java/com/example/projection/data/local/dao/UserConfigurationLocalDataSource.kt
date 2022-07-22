package com.example.projection.data.local.dao

import androidx.room.*
import com.example.projection.data.local.model.UserConfigurationRow
import com.example.projection.data.remote.model.UserConfiguration
import kotlinx.coroutines.flow.Flow

@Dao
interface UserConfigurationLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userConfiguration: UserConfigurationRow)

    @Query("SELECT * FROM user_configuration WHERE id=:userId")
    fun getUserConfiguration(userId: String): Flow<UserConfigurationRow>

    @Update(UserConfigurationRow::class)
    suspend fun updateUserConfiguration(configuration: UserConfiguration)
}
