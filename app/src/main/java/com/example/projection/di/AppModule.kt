package com.example.projection.di

import android.content.Context
import androidx.room.Room
import com.example.projection.data.local.ProjectionDatabase
import com.example.projection.data.local.dao.GroupbuyDetailLocalDataSource
import com.example.projection.data.remote.endpoint.GroupbuyDetailRemoteDataSource
import com.example.projection.data.remote.endpoint.GroupbuyIndexRemoteDataSource
import com.example.projection.data.remote.endpoint.InterestCheckIndexRemoteDataSource
import com.example.projection.data.repository.*
import com.example.projection.view.ui.theme.ThemeService
import com.example.projection.view.ui.theme.ThemeServiceImpl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.sianaki.flowretrofitadapter.FlowCallAdapterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient().newBuilder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create()
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ProjectionDatabase {
        val databaseName = "projection_db"
        return Room.databaseBuilder(
            context.applicationContext,
            ProjectionDatabase::class.java,
            databaseName
        ).build()
    }

    @Singleton
    @Provides
    fun provideGroupbuyRemoteDataSource(retrofit: Retrofit): GroupbuyIndexRemoteDataSource {
        return retrofit.create(GroupbuyIndexRemoteDataSource::class.java)
    }

    @Singleton
    @Provides
    fun provideGroupbuyRepository(
        database: ProjectionDatabase,
        remoteDataSource: GroupbuyIndexRemoteDataSource,
    ): GroupbuyIndexRepository {
        return GroupbuyIndexRepositoryImpl(database.groupbuyDao(), remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideInterestCheckRemoteDataSource(retrofit: Retrofit): InterestCheckIndexRemoteDataSource {
        return retrofit.create(InterestCheckIndexRemoteDataSource::class.java)
    }

    @Singleton
    @Provides
    fun provideInterestCheckRepository(
        database: ProjectionDatabase,
        remoteDataSource: InterestCheckIndexRemoteDataSource,
    ): InterestCheckRepository {
        return InterestCheckRepositoryImpl(database.interestCheckDao(), remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideSavedIndexRepository(
        database: ProjectionDatabase,
    ): SavedIndexRepository {
        return SavedIndexRepositoryImpl(database.savedIndexDao())
    }

    @Singleton
    @Provides
    fun provideGroupbuyDetailRemoteDataSource(retrofit: Retrofit): GroupbuyDetailRemoteDataSource {
        return retrofit.create(GroupbuyDetailRemoteDataSource::class.java)
    }

    @Singleton
    @Provides
    fun provideGroupbuyDetailLocalDataSource(
        database: ProjectionDatabase
    ): GroupbuyDetailLocalDataSource {
        return database.groupbuyDetailDao()
    }

    @Singleton
    @Provides
    fun provideThemeService(): ThemeService {
        return ThemeServiceImpl()
    }
}
