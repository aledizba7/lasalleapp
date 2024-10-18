package com.example.lasalleapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lasalleapp.R
import com.example.lasalleapp.ui.components.ScreenTemplate
import com.example.lasalleapp.ui.components.SettingTemplate
import com.example.lasalleapp.ui.theme.LaSalleAppTheme

@Composable
fun ThemeScreen(innerPadding: PaddingValues) {
    var showSuccessMessage by remember { mutableStateOf(false) }

    SettingTemplate(innerPadding = innerPadding, header = {
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
                text = "Cambiar Tema",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
    }, body = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { showSuccessMessage = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
                modifier = Modifier.height(100.dp)
                    .width(300.dp)
            ) {
                Text("Cambiar tema",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp)
            }
            if (showSuccessMessage) {
                Text(
                    "Tema cambiado exitosamente.",
                    color = Color.Green,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    })
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChangeThemeScreenPreview() {
    LaSalleAppTheme {
        ThemeScreen(innerPadding = PaddingValues(0.dp))
    }
}