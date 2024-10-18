package com.example.lasalleapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.lasalleapp.R
import com.example.lasalleapp.ui.components.ScreenTemplate
import com.example.lasalleapp.ui.components.SettingTemplate
import com.example.lasalleapp.ui.theme.LaSalleAppTheme
import com.example.lasalleapp.ui.utils.Screens

@Composable
fun SettingsScreen(innerPadding: PaddingValues, navController: NavController){
    val options = listOf(
        "Cambiar contraseña",
        "Cambiar tema",
        "Notificaciones",
        "General",
        "Acerca de",
        "Cerrar sesión"
    )

    SettingTemplate(
        innerPadding = innerPadding,
        header = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray.copy(alpha = 0.2f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Circular Image
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://i.pinimg.com/550x/d9/a6/bc/d9a6bc17a44f2c42a79fad66c4b5c053.jpg")
                            .placeholder(R.drawable.ale)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Imagen del alumno",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(130.dp)
                            .clip(CircleShape),
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Student Details
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Alejandra Díaz Barajas",
                            fontWeight = FontWeight.Bold,
                            fontSize = 29.sp
                        )
                        Text(
                            "Fecha de nacimiento: 07/02/2004",
                            fontSize = 17.sp
                        )
                        Text(
                            "adb76784@lasallebajio.edu.com",
                            fontSize = 17.sp
                        )
                    }
                }
            }
        },
        body = {
            LazyColumn(modifier = Modifier.padding(20.dp)) {
                items(options) { option ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                when (option) {
                                    "Cambiar contraseña" -> navController.navigate("changePassword")
                                    "Cambiar tema" -> navController.navigate("changeTheme")
                                    "Notificaciones" -> navController.navigate(Screens.Settings.route)
                                    "General" -> navController.navigate(Screens.Settings.route)
                                    "Acerca de" -> navController.navigate(Screens.Settings.route)
                                    "Cerrar sesión" -> navController.navigate(Screens.Settings.route)
                                }
                            }
                            .padding(vertical = 12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(option, fontSize = 18.sp)
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = null)
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsScreenPreview() {
    val navController = rememberNavController()
    LaSalleAppTheme{
        SettingsScreen(innerPadding = PaddingValues(0.dp), navController = navController)
    }
}