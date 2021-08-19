package com.delsi.bestsellerlist.di

import android.util.Log
import com.delsi.bestsellerlist.data.AppConst
import com.delsi.bestsellerlist.data.remote.AppService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val loggingInterceptor = HttpLoggingInterceptor {
        Log.d("HTTP BODY", it)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun getOkHttpClient() = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    fun getRetrofitClient(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(AppConst.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun beersService(retrofitClient: Retrofit) = retrofitClient.create(AppService::class.java)
}
