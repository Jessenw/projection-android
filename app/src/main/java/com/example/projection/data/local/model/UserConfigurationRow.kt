package com.example.projection.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projection.data.remote.model.UserConfiguration

@Entity(tableName = "user_configuration")
data class UserConfigurationRow(
    @PrimaryKey @ColumnInfo val id: String,
    @ColumnInfo val theme: Int
)

/**
 * An intermediate object used for updating the theme
 * column for a user's configuration
 */
data class UserConfigurationTheme(
    val id: Int,
    val theme: Int
)

fun UserConfigurationRow.toUserConfiguration(): UserConfiguration {
    return UserConfiguration(id, theme)
}
