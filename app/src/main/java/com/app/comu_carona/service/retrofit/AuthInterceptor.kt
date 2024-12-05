package com.app.comu_carona.service.retrofit

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

class AuthInterceptor(private val authPreferences: AuthPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val token = authPreferences.accessToken
        if(token != null) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        // Log the request
        val request = requestBuilder.build()
        Log.d("AuthInterceptor", "Request: Method - ${request.method}, URL - ${request.url}")

        val response = chain.proceed(request)
        val responseBody = response.body
        val responseBodyString = responseBody?.string()

        // Log the response
        Log.d("AuthInterceptor", "Response: ${response.code} - $responseBodyString")

        return response.newBuilder()
            .body(ResponseBody.create(responseBody?.contentType(), responseBodyString ?: ""))
            .build()
    }
}