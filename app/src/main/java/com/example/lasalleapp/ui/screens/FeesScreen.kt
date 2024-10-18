package com.example.lasalleapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lasalleapp.R
import com.example.lasalleapp.models.Fees
import com.example.lasalleapp.models.Student
import com.example.lasalleapp.models.subjects
import com.example.lasalleapp.ui.components.ScreenTemplate
import com.example.lasalleapp.ui.components.ScreenTemplate
import com.example.lasalleapp.ui.theme.BlueDark
import com.example.lasalleapp.ui.theme.DarkRed
import com.example.lasalleapp.ui.theme.GrayLight
import com.example.lasalleapp.ui.theme.GreenDark
import com.example.lasalleapp.ui.theme.LaSalleAppTheme

@Composable
fun FeesScreen(innerPadding: PaddingValues, navController: NavController) {
    val feesList = remember { mutableStateListOf(
        Fees("AGOSTO", true, "10/08/2024"),
        Fees("SEPTIEMBRE", true, "12/09/2024"),
        Fees("OCTUBRE", false, "09/10/2024"),
        Fees("NOVIEMBRE", false, "15/11/2024"),
        Fees("DICIEMBRE", false, "05/12/2024")
    ) }

    val student = Student(
        name = "Alejandra Díaz Barajas",
        career = "ISSC",
        semester = 5,
        subjects = subjects
    )

    ScreenTemplate(innerPadding= innerPadding,
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
                    text = "Pagos",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(top = 100.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray.copy(alpha = 0.2f)
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "${student.name}",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        "Carrera: ${student.career}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        "Semestre: ${student.semester}",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        },
        body = {
            Column {
                Text(
                    text = "PAGOS PENDIENTES",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineMedium,
                    color = BlueDark
                )
                LazyColumn(modifier = Modifier.padding(8.dp)) {
                    items(feesList.filter { !it.isPaid }) { fee ->
                        FeeItem(fee) { updatedFee ->
                            val index = feesList.indexOf(fee)
                            if (index != -1) {
                                feesList[index] = updatedFee
                            }
                        }
                    }
                }

                Text(
                    text = "PAGOS REALIZADOS",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineMedium,
                    color = BlueDark
                )
                LazyColumn(modifier = Modifier.padding(8.dp)) {
                    items(feesList.filter { it.isPaid }) { fee ->
                        FeeItem(fee) { updatedFee ->
                            val index = feesList.indexOf(fee)
                            if (index != -1) {
                                feesList[index] = updatedFee
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun FeeItem(fee: Fees, onPayClicked: (Fees) -> Unit) {
    Card( // Add a Card for the border
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(fee.month, style = MaterialTheme.typography.bodyMedium)
            }
            Column(modifier =Modifier.weight(1f)) {
                Text("Fecha de vencimiento: ${fee.dueDate}", style = MaterialTheme.typography.bodySmall)
            }
            Column(modifier = Modifier.weight(1f)) {
                if (fee.isPaid) {
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Pagado",
                            tint = GreenDark,
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Text("PAGADO",
                            style = MaterialTheme.typography.bodySmall,
                            color = GreenDark
                        )
                    }
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Pendiente",
                            tint = DarkRed
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Button(
                            onClick = { onPayClicked(fee.copy(isPaid = true)) },
                            colors = ButtonDefaults.buttonColors(containerColor = DarkRed)
                        ) {Text("PAGAR",
                            fontSize = 10.sp)
                        }
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun FeesScreenPreview() {
    val navController = rememberNavController()
    LaSalleAppTheme {
        FeesScreen(innerPadding = PaddingValues(0.dp), navController = navController)
    }
}