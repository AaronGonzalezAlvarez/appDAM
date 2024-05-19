package com.example.appdam.retrofit

import android.util.Log
import com.example.appdam.utils.ShowDataRest
import okhttp3.Interceptor
import okhttp3.Response

class CookiesInterceptor : Interceptor {
    private var jwtMobile: String? = null
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val modifiedRequestBuilder = request.newBuilder()
        if (jwtMobile != null) {
            modifiedRequestBuilder.addHeader("Authorization", "$jwtMobile")
        }
        val modifiedRequest = modifiedRequestBuilder.build()
        val response = chain.proceed(modifiedRequest)
        if (!response.isSuccessful) {
            val responseBody = response.body
            val errorBody = responseBody?.string()
            Log.e("ERRORPETICION", "Error body: $errorBody")
            ShowDataRest.text = errorBody ?: ""
        }
        val cookies = response.headers("Set-Cookie")
        //prueba
        if(response.request.url.toString().contains("login/CloserSession")){
            jwtMobile=null
        }
        //fin prueba
        for (cookie in cookies) {
            if (cookie.startsWith("jwtMobile=")) {
                jwtMobile = cookie.split(";")[0].substringAfter("=")
            }
        }
        return response
    }
}