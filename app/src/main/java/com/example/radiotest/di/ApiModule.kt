package com.example.radiotest.di

import android.os.SystemClock
import android.util.Log
import com.example.radiotest.BASE_URL
import com.example.radiotest.data.service.RadioService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor {
       Log.d("API", it)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    @Singleton
    @Provides
    fun provideOKHttpClint(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        val interceptor = Interceptor { chain ->
            SystemClock.sleep(500)
            chain.proceed(chain.request())
        }

        val okHttpClient = OkHttpClient.Builder()
            .dispatcher(dispatcher)
            .addNetworkInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
        return okHttpClient
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit
        .Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideRadioService(retrofit: Retrofit) = retrofit.create(RadioService::class.java)

}