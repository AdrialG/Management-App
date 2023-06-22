package com.adrialg.managementapp.injection

import android.content.Context
import com.adrialg.managementapp.api.ApiService
import com.adrialg.managementapp.data.AppDatabase
import com.adrialg.managementapp.data.Const
import com.crocodic.core.data.CoreSession
import com.crocodic.core.helper.NetworkHelper
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = AppDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()

    @Singleton
    @Provides
    fun provideOkHttpClient(session: CoreSession): OkHttpClient {
        return NetworkHelper.provideOkHttpClient().newBuilder().apply {
            addInterceptor {
                val original = it.request()
                val requestBuilder = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method, original.body)

                val token = session.getString(Const.TOKEN.API_TOKEN)
                if (token.isNotEmpty()) {
                    requestBuilder.header("Authorization", "Bearer $token")
                }

                val request = requestBuilder.build()
                it.proceed(request)
            }
        }.build()
    }

    @Singleton
    @Provides
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return NetworkHelper.provideApiService(
            baseUrl = "https://magang.crocodic.net/ki/kelompok_3/project_management/public/api/",
            okHttpClient = okHttpClient,
            converterFactory = listOf(ScalarsConverterFactory.create())
        )
    }

}