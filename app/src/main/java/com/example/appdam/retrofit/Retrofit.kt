package com.example.appdam.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    var baseUrl: String = "http://192.168.1.10:8080/"

    // Método para cambiar la baseUrl
    fun setUrl(newBaseUrl: String) {
        baseUrl = newBaseUrl
        // Resetear retrofit para que se cree una nueva instancia
        retrofit = null
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient()) // Usa el cliente personalizado aquí
            .build()
    }

    fun getOkHttpClient(): OkHttpClient {
        try {
            val httpClientBuilder = OkHttpClient.Builder()
            httpClientBuilder.addInterceptor(CookiesInterceptor())
            return httpClientBuilder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
    var retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient()) // Usa el cliente personalizado aquí
        .build()
}