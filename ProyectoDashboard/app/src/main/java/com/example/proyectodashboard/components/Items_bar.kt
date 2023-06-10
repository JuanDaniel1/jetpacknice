package com.example.proyectodashboard.components
import com.example.proyectodashboard.R

sealed class Items_bar(
    val icon: Int,
    val title: String,
    val ruta: String
) {
    object Boton1: Items_bar(R.drawable.bicicleta, "Inicio", "boton1")
    object Boton2: Items_bar(R.drawable.moto, "Contenidos", "boton2")
    object Boton3: Items_bar(R.drawable.flechas, "Informacion", "boton3")
}
