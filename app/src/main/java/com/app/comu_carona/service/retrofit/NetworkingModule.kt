package com.app.comu_carona.service.retrofit

import okhttp3.OkHttpClient
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Single
fun provideRetrofit(authPreferences: AuthPreferences): Retrofit {
    return Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .client(OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(authPreferences))
            .build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}