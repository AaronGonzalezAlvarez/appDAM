package com.example.appdam.uiState

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.graphics.vector.ImageVector

data class LoginUiState(
    val username:String = "manuel@example.com",
    val password:String = "usuario",
    val message:Boolean = false,
    val text:String = "",
    val ip:String = "192.168.1.105",
    val progress:Boolean = false,
    val iconPass: ImageVector = Icons.Filled.VisibilityOff,
    val seePass: Boolean = false
)
