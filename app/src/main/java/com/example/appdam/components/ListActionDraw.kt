package com.example.appdam.components

import androidx.compose.ui.graphics.vector.ImageVector

data class ListActionDraw(
    val text: String,
    val icon: ImageVector,
    val action: () -> Unit
)