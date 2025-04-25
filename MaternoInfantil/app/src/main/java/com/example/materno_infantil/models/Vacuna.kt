package com.example.materno_infantil.models


data class Vacuna (
    val nombre: String,
    val semanasDesdeFUM: Long? = null, // para embarazo
    val mesesDesdeNacimiento: Long? = null, // para bebé
    val recibida: Boolean = false
)