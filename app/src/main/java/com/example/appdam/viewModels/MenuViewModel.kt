package com.example.appdam.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.appdam.nav.Routes
import com.example.appdam.retrofit.model.activity.Activity
import com.example.appdam.retrofit.model.activity.oneActivity.OneActivity
import com.example.appdam.retrofit.model.myInscription.MyInscription
import com.example.appdam.retrofit.objects.ActivityRetrofit
import com.example.appdam.retrofit.objects.LoginRetrofit
import com.example.appdam.uiState.MenuUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar


class MenuViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MenuUiState())
    val uiState: StateFlow<MenuUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val activities: Activity = ActivityRetrofit.getActivitiesAll() ?: Activity()

            val inscription: MyInscription = ActivityRetrofit.getInscriptions() ?: MyInscription()

            val calendar = Calendar.getInstance()
            val anno = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            var monthAux =month.toString()

            if(month<10){
                monthAux ="0${month}"
            }

            var today:String = "${anno}-${monthAux}-${day}"
            var num:Int = 0
            inscription.forEach{ x->
                var dataAux = x.date
                if(dataAux.equals(today)){
                    num++
                }
            }

            _uiState.update { currentState ->
                currentState.copy(
                    activityTotal = activities,
                    inscription = inscription,
                    totalActivityNow = num,
                    today=today
                )
            }

        }
    }

    /*fun menssageFunFalse(){
        _uiState.update { currentState -> currentState.copy(
            message = false
        ) }
    }*/

    fun addInscription(id:Int){
        viewModelScope.launch {
            ActivityRetrofit.addInscription(id)
            reset()
        }
    }

    fun deleteInscription(id:Int){
        viewModelScope.launch {
            ActivityRetrofit.deleteInscriptions(id)
            reset()
        }
    }

    fun info(id: Int, navController: NavHostController){
            navController.navigate(route = Routes.ScreenActivity.route+"/${id}")
    }

    private fun reset(){
        viewModelScope.launch {
            val inscription: MyInscription = ActivityRetrofit.getInscriptions() ?: MyInscription()

            _uiState.update { currentState ->
                currentState.copy(
                    inscription = inscription,
                )
            }
        }
    }

    fun closeSession() {
        viewModelScope.launch {
            LoginRetrofit.CloserSession()
        }
    }
}