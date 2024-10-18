package com.example.lasalleapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.lasalleapp.R
import com.example.lasalleapp.models.PartialGrade
import com.example.lasalleapp.models.Subject
import com.example.lasalleapp.ui.components.ScreenTemplate
import com.example.lasalleapp.ui.components.SettingTemplate
import com.example.lasalleapp.ui.theme.LaSalleAppTheme

@Composable
fun SubjectPartialsScreen(subject: Subject, innerPadding: PaddingValues) {
    SettingTemplate(
        innerPadding = innerPadding,
        header = {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
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
                    text = subject.name,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
        },
        body = {
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(subject.partialGrades) { partialGrade ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Parcial ${partialGrade.partialNumber}", style = MaterialTheme.typography.bodyLarge)
                            Text("${partialGrade.grade}", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SubjectDetailsScreenPreview() {
    val navController = rememberNavController()
    LaSalleAppTheme {
        // Sample Subject object for preview
        val subject = Subject(
            id = 1,
            name = "POO",
            averageGrade = 8.8f,
            partialGrades = listOf(
                PartialGrade(1, 8.5f),
                PartialGrade(2, 9.0f),
                PartialGrade(3, 9.2f)
            )
        )
        SubjectPartialsScreen(subject = subject, innerPadding = PaddingValues(0.dp))
    }
}