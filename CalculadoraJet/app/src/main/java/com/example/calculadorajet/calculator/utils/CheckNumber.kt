package com.example.calculadorajet.calculator.utils

fun isNumber(s: String): Boolean {
    return  if (s.isEmpty()) false else s.all { Character.isDigit(it) || s == "." }
}

fun isEqual(m: String): Boolean {
    return if(m.isEmpty()) false else m == "="
}
fun isParent(z: String): Boolean {
    return if(z.isEmpty()) false else z == "(" || z == ")"
}