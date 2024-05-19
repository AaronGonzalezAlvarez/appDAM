package com.example.appdam.retrofit.interfaces

import com.example.appdam.retrofit.model.login.Login
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginInterface {

    @POST("login/access")
    suspend fun login(@Body data: Login)

    @GET("login/CloserSession")
    suspend fun CloserSession()

}