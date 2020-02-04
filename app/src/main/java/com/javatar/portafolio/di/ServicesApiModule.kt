package com.javatar.portafolio.di

import com.javatar.portafolio.api.ServicesApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ServicesApiModule constructor(baseUrl: String) {

    var baseUrl: String? = ""

    init {
        this.baseUrl = baseUrl
    }

    @Singleton
    @Provides
    fun getLogInterceptor(): Interceptor {
        var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(interactorLog: Interceptor): OkHttpClient {

        return OkHttpClient.Builder()
            .readTimeout(1200, TimeUnit.SECONDS)
            .connectTimeout(1200, TimeUnit.SECONDS)
            .addInterceptor(interactorLog)
            .build()
    }

    @Singleton
    @Provides
    fun getApi(client: OkHttpClient): ServicesApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
            .create(ServicesApi::class.java)

    }

}