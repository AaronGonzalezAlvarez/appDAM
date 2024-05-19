package com.example.appdam.uiState

import com.example.appdam.retrofit.model.activity.oneActivity.OneActivity
import com.example.appdam.retrofit.model.myInscription.MyInscription

data class ActivityUiState(
    val activity: String = "",
    val today:String ="",
    val inscription: MyInscription? = null,
    val id:String=""
)
