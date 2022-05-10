package com.example.projection.di

import android.content.Context
import androidx.room.Room
import com.example.projection.data.local.ProjectionDatabase
import com.example.projection.data.remote.groupbuy.GroupbuyRemoteDataSource
import com.example.projection.data.remote.groupbuy.GroupbuyRepository
import com.example.projection.data.remote.groupbuy.GroupbuyRepositoryImpl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
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
    fun provideRemoteDataSource(retrofit: Retrofit): GroupbuyRemoteDataSource {
        return retrofit.create(GroupbuyRemoteDataSource::class.java)
    }

    @Singleton
    @Provides
    fun provideGroupbuyRepository(
        database: ProjectionDatabase,
        remoteDataSource: GroupbuyRemoteDataSource,
    ): GroupbuyRepository {
        return GroupbuyRepositoryImpl(database.groupbuyDao(), remoteDataSource)
    }
}
