package com.example.appdam.retrofit.interfaces

import com.example.appdam.retrofit.model.activity.Activity
import com.example.appdam.retrofit.model.activity.delete.IdActivity
import com.example.appdam.retrofit.model.activity.oneActivity.OneActivity
import com.example.appdam.retrofit.model.login.Login
import com.example.appdam.retrofit.model.myInscription.MyInscription
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ActivityInterface {

    @GET("activity/getActivitiesAll")
    suspend fun getActivitiesAll(): Activity

    @GET("activity/getInscriptions")
    suspend fun getInscriptions(): MyInscription

    @DELETE("activity/deleteInscriptions")
    suspend fun deleteInscriptions(@Query("id") id: String)

    @GET("activity/addInscriptions")
    suspend fun addInscription(@Query("id") id: String)

    @GET("activity/getActivity")
    suspend fun getActivity(@Query("id") id: String): OneActivity

}