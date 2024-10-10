package com.example.lasalleapp.models

import androidx.compose.ui.graphics.vector.ImageVector
import okhttp3.Route

data class BottomNavigationItem(
    val title : String,
    val icon : ImageVector,
    val route: String
)