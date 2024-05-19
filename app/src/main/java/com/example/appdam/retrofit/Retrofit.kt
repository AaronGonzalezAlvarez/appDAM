package com.example.appdam.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    var baseUrl: String = "http://192.168.1.105:8080/"

    fun getOkHttpClient(): OkHttpClient {
        try {
            val httpClientBuilder = OkHttpClient.Builder()
            httpClientBuilder.addInterceptor(CookiesInterceptor())
            return httpClientBuilder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient()) // Usa el cliente personalizado aquí
        .build()
}