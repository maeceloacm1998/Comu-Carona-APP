package com.app.comu_carona.service.retrofit

import okhttp3.OkHttpClient
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

@Single
fun provideRetrofit(authPreferences: AuthPreferences): Retrofit {
    val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(authPreferences))
        .connectTimeout(30, SECONDS)
        .readTimeout(30, SECONDS)
        .writeTimeout(30, SECONDS)
        .build()

    return Retrofit.Builder()
        .baseUrl("https://97bc-177-14-160-37.ngrok-free.app ")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}