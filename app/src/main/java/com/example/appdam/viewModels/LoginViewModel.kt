package com.example.appdam.viewModels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.appdam.nav.AppNav
import com.example.appdam.nav.Routes
import com.example.appdam.retrofit.model.login.Login
import com.example.appdam.retrofit.objects.LoginRetrofit
import com.example.appdam.uiState.LoginUiState
import com.example.appdam.utils.ShowDataRest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun usernameChange(user: String) {
        _uiState.update { currentState ->
            currentState.copy(
                username = user
            )
        }
    }

    fun passwordChange(pass: String) {
        _uiState.update { currentState ->
            currentState.copy(
                password = pass
            )
        }
    }

    fun menssageFunFalse(){
        _uiState.update { currentState -> currentState.copy(
            message = false
        ) }
    }

    fun resetData() {
        _uiState.update {
            LoginUiState(
                password = "",
                username = "",
            )
        }
    }

    fun showPass() {
        if(_uiState.value.iconPass == Icons.Filled.VisibilityOff){
            _uiState.update { currentState -> currentState.copy(
                iconPass = Icons.Filled.Visibility,
                seePass = true
            ) }
        }else{
            _uiState.update { currentState -> currentState.copy(
                iconPass = Icons.Filled.VisibilityOff,
                seePass = false
            ) }
        }
    }

    fun saveConnection(navController: NavHostController) {
        val userName = _uiState.value.username
        val pass = _uiState.value.password
        if (userName.isNotEmpty() && pass.isNotEmpty()) {
            viewModelScope.launch {
                val result: Boolean = LoginRetrofit.postLogin(Login(userName,pass))
                if(result){
                    navController.navigate(route = Routes.ScreenMenu.route)
                }else{
                    _uiState.update { currentState -> currentState.copy(
                        text = ShowDataRest.text,
                        message=true
                    ) }
                }
            }
        } else{
            _uiState.update { currentState -> currentState.copy(
                text = "Campo usuairo o contraseña vacío",
                message=true
            ) }
        }
    }
}