package com.example.lasalleapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lasalleapp.R
import com.example.lasalleapp.models.Student
import com.example.lasalleapp.models.subjects
import com.example.lasalleapp.ui.components.ScreenTemplate
import com.example.lasalleapp.ui.theme.LaSalleAppTheme

@Composable
fun GradesScreen(innerPadding: PaddingValues, navController: NavController) {
    // Crear instancia del estudiante con sus datos (nombre, carrera, semestre y materias)
    val student = Student(
        name = "Alejandra Díaz Barajas",
        career = "ISSC",
        semester = 5,
        subjects = subjects
    )

    // Estructura principal de la pantalla que acepta encabezado y cuerpo
    ScreenTemplate(
        innerPadding = innerPadding,
        header = {
            // Fila para mostrar la cabecera con el logo y el título "Calificaciones"
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp), // Añade un margen alrededor
                verticalAlignment = Alignment.CenterVertically // Alinea elementos verticalmente
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.logo // Carga el recurso de imagen del logo
                    ),
                    contentDescription = "Logo", // Descripción para accesibilidad
                    modifier = Modifier.size(70.dp) // Define el tamaño de la imagen
                )

                Text(
                    text = "Calificaciones",
                    color = Color.White, // Color del texto
                    style = MaterialTheme.typography.headlineLarge, // Estilo del texto
                    modifier = Modifier.padding(16.dp) // Margen alrededor del texto
                )
            }

            // Tarjeta que muestra información del estudiante (nombre, carrera, semestre)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp) // Margen alrededor de la tarjeta
                    .padding(top = 100.dp), // Margen adicional en la parte superior
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray.copy(alpha = 0.2f) // Color de fondo translúcido
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "${student.name}", // Nombre del estudiante
                        style = MaterialTheme.typography.titleLarge // Estilo del nombre
                    )
                    Text(
                        "Carrera: ${student.career}", // Carrera del estudiante
                        style = MaterialTheme.typography.titleMedium // Estilo de texto
                    )
                    Text(
                        "Semestre: ${student.semester}", // Semestre actual del estudiante
                        style = MaterialTheme.typography.titleMedium // Estilo de texto
                    )
                }
            }
        },
        body = {
            // Lista vertical que muestra las materias y sus calificaciones promedio
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(subjects) { subject ->
                    // Tarjeta clicable para cada materia que navega a una nueva pantalla al hacer clic
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate("subjectPartials/${subject.id}") } // Navega a detalles de la materia
                            .padding(8.dp) // Margen alrededor de cada tarjeta
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp), // Espaciado dentro de la tarjeta
                            horizontalArrangement = Arrangement.SpaceBetween // Espacia los elementos entre sí
                        ) {
                            Text(subject.name, fontSize = 18.sp) // Nombre de la materia
                            Text("${subject.averageGrade}", fontSize = 18.sp) // Calificación promedio de la materia
                        }
                    }
                }
            }

            // Sección inferior que muestra el promedio acumulado del estudiante
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 400.dp) // Desplazamiento desde la parte superior
                    .padding(16.dp) // Margen alrededor del Box
            ){
                Text(
                    "Promedio Acumulado: ${student.accumulatedAverage}", // Texto que muestra el promedio acumulado
                    fontSize = 20.sp, // Tamaño del texto
                    modifier = Modifier.padding(16.dp) // Margen alrededor del texto
                )
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GradesScreenPreview() {
    // Previsualización de la pantalla para mostrar cómo se verá la interfaz
    val navController = rememberNavController() // Creación del controlador de navegación
    LaSalleAppTheme { // Aplicación del tema visual
        GradesScreen(innerPadding = PaddingValues(0.dp), navController = navController) // Llamada a la función composable principal
    }
}