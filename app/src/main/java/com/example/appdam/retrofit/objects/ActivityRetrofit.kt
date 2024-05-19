package com.example.appdam.retrofit.objects

import android.util.Log
import com.example.appdam.retrofit.Retrofit
import com.example.appdam.retrofit.interfaces.ActivityInterface
import com.example.appdam.retrofit.model.activity.Activity
import com.example.appdam.retrofit.model.activity.delete.IdActivity
import com.example.appdam.retrofit.model.activity.oneActivity.OneActivity
import com.example.appdam.retrofit.model.myInscription.MyInscription

object ActivityRetrofit {

    suspend fun getActivitiesAll(): Activity? {
        val apiService = Retrofit.retrofit.create(ActivityInterface::class.java)
        return try {
            apiService.getActivitiesAll()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getActivity(id:String): OneActivity {
        val apiService = Retrofit.retrofit.create(ActivityInterface::class.java)
        return apiService.getActivity(id)

    }

    suspend fun getInscriptions(): MyInscription? {
        val apiService = Retrofit.retrofit.create(ActivityInterface::class.java)
        return try {
            apiService.getInscriptions()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun deleteInscriptions(data:Int): Boolean {
        val apiService = Retrofit.retrofit.create(ActivityInterface::class.java)
        return try {
            apiService.deleteInscriptions(data.toString())
            true
        } catch (e: Exception) {
            Log.e("temaInscripciones", "Error al eliminar inscripciones", e)
            false
        }
    }

    suspend fun addInscription(id:Int): Boolean {
        val apiService = Retrofit.retrofit.create(ActivityInterface::class.java)
        return try {
            apiService.addInscription(id.toString())
            true
        } catch (e: Exception) {
            Log.e("temaInscripciones", "Error al eliminar inscripciones", e)
            false
        }
    }
}