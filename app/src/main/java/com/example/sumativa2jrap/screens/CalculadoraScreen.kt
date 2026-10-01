package com.example.sumativa2jrap.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sumativa2jrap.components.CampoFormulario
import com.example.sumativa2jrap.components.ResumenMonto
import java.util.Locale

@Composable
fun CalculadoraScreen() {
    var nombre by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var porcentaje by remember { mutableStateOf("") }
    var propina by remember { mutableStateOf(0.0) }
    var total by remember { mutableStateOf(0.0) }

    var errorNombre by remember { mutableStateOf<String?>(null) }
    var errorCantidad by remember { mutableStateOf<String?>(null) }
    var errorPrecio by remember { mutableStateOf<String?>(null) }
    var errorPorcentaje by remember { mutableStateOf<String?>(null) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            ResumenMonto("Propina", formatoDinero(propina), Modifier.weight(1f))
            ResumenMonto("Total a pagar", formatoDinero(total), Modifier.weight(1f))
        }



        Spacer(modifier = Modifier.height(12.dp))


        CampoFormulario("Nombre del platillo", nombre, {
            nombre = it
            errorNombre = null
        }, error = errorNombre)

        CampoFormulario("Cantidad", cantidad, {
            cantidad = it
            errorCantidad = null
        }, KeyboardType.Decimal, errorCantidad)

        CampoFormulario("Precio", precio, {
            precio = it
            errorPrecio = null
        }, KeyboardType.Decimal, errorPrecio)

        CampoFormulario("Porcentaje de propina (máx. 10%)", porcentaje, {
            porcentaje = it
            errorPorcentaje = null
        }, KeyboardType.Decimal, errorPorcentaje)

        Spacer(modifier = Modifier.height(8.dp))



        Button(
            onClick = {
                    val numeroCantidad = cantidad.toDoubleOrNull()
                    val numeroPrecio = precio.toDoubleOrNull()
                    val numeroPorcentaje = porcentaje.toDoubleOrNull()

                errorNombre = if (nombre.isBlank()) "Ingrese el nombre del platillo" else null
                errorCantidad = when {
                    cantidad.isBlank() -> "Ingrese la cantidad"
                    numeroCantidad == null || numeroCantidad <= 0 || !numeroCantidad.isFinite() -> "Ingrese una cantidad mayor que 0"
                    else -> null
                }
                errorPrecio = when {
                    precio.isBlank() -> "Ingrese el precio"
                    numeroPrecio == null || numeroPrecio < 0 || !numeroPrecio.isFinite() -> "Ingrese un precio válido"
                    else -> null
                }
                errorPorcentaje = when {
                    porcentaje.isBlank() -> "Ingrese el porcentaje"
                    numeroPorcentaje == null || !numeroPorcentaje.isFinite() || numeroPorcentaje !in 0.0..10.0 -> "La propina debe estar entre 0% y 10%"
                    else -> null
                }

                if (errorNombre == null && errorCantidad == null &&
                    errorPrecio == null && errorPorcentaje == null
                ) {
                    val subtotal = numeroCantidad!! * numeroPrecio!!
                    propina = subtotal * numeroPorcentaje!! / 100
                    total = subtotal + propina
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF263238)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular Total")
        }



        OutlinedButton(
            onClick = {
                nombre = ""
                cantidad = ""
                precio = ""
                porcentaje = ""
                propina = 0.0
                total = 0.0
                errorNombre = null
                errorCantidad = null
                errorPrecio = null
                errorPorcentaje = null
            },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color(0xFFE8EDF0),
                contentColor = Color(0xFF263238)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Limpiar")
        }
    }
}







private fun formatoDinero(valor: Double): String = String.format(Locale.US, "$%.2f", valor)
