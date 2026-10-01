package com.example.sumativa2jrap.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ResumenMonto(titulo: String, monto: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = titulo, style = MaterialTheme.typography.titleMedium)
        Text(text = monto, fontSize = 28.sp, fontWeight = FontWeight.Bold)
    }
}
