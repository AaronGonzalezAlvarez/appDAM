package com.example.appdam

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.lifecycleScope
import com.example.appdam.nav.AppNav
import com.example.appdam.retrofit.model.login.Login
import com.example.appdam.retrofit.objects.ActivityRetrofit
import com.example.appdam.retrofit.objects.LoginRetrofit
import com.example.appdam.screens.LoginScreen
import com.example.appdam.ui.theme.AppDAMTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppDAMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.purple_200)
                ) {
                    AppNav()
                }
            }
        }
    }
}
