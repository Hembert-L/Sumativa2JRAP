package com.example.sumativa2jrap.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions

@Composable
fun CampoFormulario(
    etiqueta: String,
    valor: String,
    alCambiar: (String) -> Unit,
    tipoTeclado: KeyboardType = KeyboardType.Text,
    error: String? = null
) {
    OutlinedTextField(
        value = valor,
        onValueChange = alCambiar,
        label = { Text(etiqueta) },
        isError = error != null,
        supportingText = if (error != null) { { Text(error) } } else null,
        keyboardOptions = KeyboardOptions(keyboardType = tipoTeclado),
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}
