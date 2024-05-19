package com.example.appdam.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appdam.retrofit.model.activity.Activity
import com.example.appdam.retrofit.model.myInscription.MyInscription
import com.example.appdam.retrofit.objects.ActivityRetrofit
import com.example.appdam.uiState.ActivityUiState
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar

class ActivityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ActivityUiState())
    val uiState: StateFlow<ActivityUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
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

            _uiState.update { currentState ->
                currentState.copy(
                    today=today,
                    inscription = inscription
                )
            }
        }
    }

    fun loadActivity(idActivity: String) {
        viewModelScope.launch {

            val gson = Gson()
            val oneActivityString = gson.toJson(ActivityRetrofit.getActivity(idActivity))
            _uiState.update { currentState ->
                currentState.copy(
                    activity = oneActivityString,
                    id=idActivity
                )
            }
        }
    }

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

    fun reset(){
        viewModelScope.launch {
            val gson = Gson()
            val oneActivityString = gson.toJson(ActivityRetrofit.getActivity(uiState.value.id))
            val inscription: MyInscription = ActivityRetrofit.getInscriptions() ?: MyInscription()
            _uiState.update { currentState ->
                currentState.copy(
                    inscription = inscription,
                    activity = oneActivityString
                )
            }
        }
    }
}