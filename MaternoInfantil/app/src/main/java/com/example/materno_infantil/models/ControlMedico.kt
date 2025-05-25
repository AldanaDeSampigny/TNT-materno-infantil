package com.example.materno_infantil.models

import java.time.LocalDate

data class ControlMedico(
    val fecha: LocalDate,
    val edadGestacional: String,
    val peso: String,
    val presion: String,
    val observaciones: String
)
