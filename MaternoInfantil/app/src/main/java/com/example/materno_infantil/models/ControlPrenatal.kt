package com.example.materno_infantil.models

import java.time.LocalDate
import java.time.temporal.WeekFields

data class ControlPrenatal(
    val titulo: String,
    val descripcion: String,
    val fechaControl: LocalDate,  // fecha en que debe realizarse o se realizó
    var realizado: Boolean = false // true si el control ya fue realizado, false si está pendiente
) {
    // Semana desde la FUM para calcular fecha, si la necesitás
    val semanaDesdeFUM: Int
        get() = fechaControl.get(WeekFields.ISO.weekOfWeekBasedYear()) // o calculado según necesites

    companion object {
        // Método ejemplo para devolver controles de prueba
        fun getControles(): List<ControlPrenatal> {
            return listOf(
                ControlPrenatal("Control 1", "Chequeo general", LocalDate.of(2025, 5, 10), false),
                ControlPrenatal("Control 2", "Ecografía", LocalDate.of(2025, 5, 15), true),
                ControlPrenatal("Control 3", "Análisis de sangre", LocalDate.of(2025, 6, 5), false)
            )
        }
    }
}
