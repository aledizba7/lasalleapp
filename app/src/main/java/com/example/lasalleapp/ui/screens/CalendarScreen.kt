package com.example.lasalleapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lasalleapp.R
import com.example.lasalleapp.ui.components.DayItem
import com.example.lasalleapp.ui.components.ScreenTemplate
import com.example.lasalleapp.ui.theme.LaSalleAppTheme
import java.util.Calendar


// PÁGINA HECHA PRINCIPALMENTE CON IA

@Composable
fun CalendarScreen(innerPadding: PaddingValues) {
    val currentMonth = remember { mutableStateOf(Calendar.getInstance().get(Calendar.MONTH)) }
    val currentYear = remember { mutableStateOf(Calendar.getInstance().get(Calendar.YEAR))}

    val monthNames = listOf("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre")

    ScreenTemplate(innerPadding = innerPadding,
        header = {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.logo
                        ),
                        contentDescription = "Logo",
                        modifier = Modifier.size(70.dp)
                    )

                    Text(
                        text = "Calendario",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(top = 30.dp),
                    text = "Octubre",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge
                )
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                ) {
                    items(31){
                        DayItem()
                    }
                }
            }
        },
        body = {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(getEventsForMonth(currentMonth.value, currentYear.value)) { event ->
                EventItem(event)
            }
        }
    })
}

data class Event(
    val day: Int,
    val title: String,
    val description: String
)

fun getEventsForMonth(month: Int, year: Int): List<Event> {
    return listOf(
        Event(5, "Examen de matemáticas", "Examen final de matemáticas"),
        Event(12, "Entrega de proyecto", "Entrega del proyecto final de física"),
        Event(20, "Día festivo", "No hay clases")
    )
}

@Composable
fun EventItem(event: Event) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = event.title, style = MaterialTheme.typography.bodyLarge)
            Text(text = event.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Día: ${event.day}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)

@Composable
fun CalendarScreenPreview(){
    LaSalleAppTheme {
        CalendarScreen(innerPadding = PaddingValues(0.dp))
    }
}