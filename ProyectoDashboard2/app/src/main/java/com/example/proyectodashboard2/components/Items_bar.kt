package com.example.proyectodashboard2.components
import com.example.proyectodashboard2.R

// se crean los botones para el navegador inferior donde se aplica el icono, titulo y ruta
sealed class Items_bar(
    val icon: Int,
    val title: String,
    val ruta: String
) {
    object Boton1: Items_bar(R.drawable.hogar, "Inicio", "boton1")
    object Boton2: Items_bar(R.drawable.contenido, "Contenidos", "boton2")
    object Boton3: Items_bar(R.drawable.informacion, "Informacion", "boton3")
}
