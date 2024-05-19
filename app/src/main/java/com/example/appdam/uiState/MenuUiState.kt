package com.example.appdam.uiState

import com.example.appdam.retrofit.model.activity.Activity
import com.example.appdam.retrofit.model.myInscription.MyInscription
import com.example.appdam.retrofit.model.myInscription.MyInscriptionItem

data class MenuUiState(
    val activityTotal: Activity? = null,
    val inscription: MyInscription? = null,
    val inscriptionToday: List<MyInscriptionItem?> = listOf(),
    val dialog:Boolean = false,
    val totalActivityNow:Int = 0,
    val today:String=""
)
