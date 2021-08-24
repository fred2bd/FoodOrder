package com.dindinn.miniassignment.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {
    private const val BASE_URL = "https://dindinn.com/"

    fun provideApiInterface(): ApiInterface {
        return provideRetrofit().create(ApiInterface::class.java)
    }


    fun provideRetrofit(): Retrofit {


        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
        val mockInterceptor = MockInterceptor()


        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(mockInterceptor)
            .connectTimeout(140, TimeUnit.SECONDS)
            .readTimeout(140, TimeUnit.SECONDS)
            .writeTimeout(140, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}