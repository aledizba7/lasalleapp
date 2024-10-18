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
fun SettingsScreen(innerPadding: PaddingValues, navController: NavController) {
    // Lista de opciones que se mostrarán en la pantalla de configuración
    val options = listOf(
        "Cambiar contraseña",
        "Cambiar tema",
        "Notificaciones",
        "General",
        "Acerca de",
        "Cerrar sesión"
    )

    // Plantilla para la pantalla de configuración, que incluye encabezado y cuerpo
    SettingTemplate(
        innerPadding = innerPadding,
        header = {
            // Tarjeta que contiene la imagen del perfil y detalles del estudiante
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
                        .padding(20.dp), // Espaciado interior de la tarjeta
                    horizontalAlignment = Alignment.CenterHorizontally, // Centra los elementos horizontalmente
                    verticalArrangement = Arrangement.Center // Centra los elementos verticalmente
                ) {
                    // Imagen circular del perfil
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(R.drawable.ale) // Ruta de la imagen a mostrar
                            .placeholder(R.drawable.profile) // Imagen de placeholder mientras se carga
                            .crossfade(true) // Efecto de transición al cargar la imagen
                            .build(),
                        contentDescription = "Imagen Alumno", // Descripción de la imagen para accesibilidad
                        contentScale = ContentScale.Crop, // Ajuste de la imagen para que se recorte al contenedor
                        modifier = Modifier
                            .size(130.dp) // Tamaño de la imagen
                            .clip(CircleShape) // Da forma circular a la imagen
                    )

                    Spacer(modifier = Modifier.height(8.dp)) // Espacio entre la imagen y el texto

                    // Detalles del estudiante: nombre, fecha de nacimiento y correo
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally // Centrado horizontal de los textos
                    ) {
                        Text(
                            "Alejandra Díaz Barajas",
                            fontWeight = FontWeight.Bold, // Resalta el nombre con negritas
                            fontSize = 29.sp // Tamaño grande para el nombre
                        )
                        Text(
                            "Fecha de nacimiento: 07/02/2004",
                            fontSize = 17.sp // Tamaño estándar para los detalles adicionales
                        )
                        Text(
                            "adb76784@lasallebajio.edu.com",
                            fontSize = 17.sp // Correo del estudiante
                        )
                    }
                }
            }
        },
        body = {
            // Contenedor para mostrar las opciones de configuración
            LazyColumn(modifier = Modifier.padding(20.dp)) {
                items(options) { option ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { // Acción al hacer clic en cada opción
                                when (option) {
                                    "Cambiar contraseña" -> navController.navigate("changePassword") // Navegar a la pantalla para cambiar contraseña
                                    "Cambiar tema" -> navController.navigate("changeTheme") // Navegar a la pantalla para cambiar el tema
                                    "Notificaciones" -> navController.navigate(Screens.Settings.route) // Navegar a la pantalla de notificaciones
                                    "General" -> navController.navigate(Screens.Settings.route) // Navegar a la configuración general
                                    "Acerca de" -> navController.navigate(Screens.Settings.route) // Navegar a la sección Acerca de
                                    "Cerrar sesión" -> navController.navigate(Screens.Settings.route) // Navegar a la opción de cerrar sesión
                                }
                            }
                            .padding(vertical = 12.dp) // Espaciado vertical para cada tarjeta
                    ) {
                        // Fila que contiene el texto de la opción y un ícono
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp), // Espaciado dentro de la fila
                            verticalAlignment = Alignment.CenterVertically // Alinea los elementos verticalmente al centro
                        ) {
                            Text(option, fontSize = 18.sp) // Muestra el texto de la opción
                            Spacer(modifier = Modifier.weight(1f)) // Espacio para separar el texto y el ícono
                            Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = null) // Ícono que indica navegación hacia adelante
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
    // Previsualización de la pantalla de configuración para ver cómo se vería en la app
    val navController = rememberNavController()
    LaSalleAppTheme {
        SettingsScreen(innerPadding = PaddingValues(0.dp), navController = navController)
    }
}