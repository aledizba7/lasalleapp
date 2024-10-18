package com.example.lasalleapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lasalleapp.models.Fees
import com.example.lasalleapp.ui.components.ScreenTemplate
import com.example.lasalleapp.ui.components.ScreenTemplate
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

    ScreenTemplate(innerPadding= innerPadding,
        header = {
            Text(
                text = "Pagos",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineLarge
            )
        },
        body = {
            Column {
                Text(
                    text = "PAGOS PENDIENTES",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
                LazyColumn(modifier = Modifier.padding(8.dp)) {
                    items(feesList.filter { !it.isPaid }) { fee ->
                        FeeItem(fee) { updatedFee ->
                            // Update the list in a thread-safe way
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
                    style = MaterialTheme.typography.headlineMedium
                )
                LazyColumn(modifier = Modifier.padding(8.dp)) {
                    items(feesList.filter { it.isPaid }) { fee ->
                        FeeItem(fee) { updatedFee ->
                            // Update the list in a thread-safe way
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
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(fee.month, style = MaterialTheme.typography.bodyMedium)
        }
        Column(modifier = Modifier.weight(1f)) {
            Text("Fecha de vencimiento: ${fee.dueDate}", style = MaterialTheme.typography.bodySmall)
        }
        Column(modifier = Modifier.weight(1f)) {
            if (fee.isPaid) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Pagado",
                    tint = Color.Green
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Pendiente",
                    tint = Color.Red
                )
                Button(onClick = { onPayClicked(fee.copy(isPaid = true)) }) {
                    Text("PAGAR")
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