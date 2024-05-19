package com.example.appdam.retrofit.objects

import com.example.appdam.retrofit.Retrofit
import com.example.appdam.retrofit.interfaces.LoginInterface
import com.example.appdam.retrofit.model.login.Login

object LoginRetrofit {

    suspend fun postLogin(data: Login): Boolean {
        val apiService = Retrofit.retrofit.create(LoginInterface::class.java)
        return try {
            apiService.login(data)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun CloserSession(): Boolean {
        val apiService = Retrofit.retrofit.create(LoginInterface::class.java)
        return try {
            apiService.CloserSession()
            true
        } catch (e: Exception) {
            false
        }
    }
}