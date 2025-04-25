package com.example.materno_infantil.models

object VacunaBebe {
    fun getVacunas(): List<Vacuna> {
        return listOf(
            Vacuna(
                nombre = "BCG",
                mesesDesdeNacimiento = 0,
                recibida = true
            ),
            Vacuna(
                nombre = "Hepatitis B (1° dosis)",
                mesesDesdeNacimiento = 0,
                recibida = true
            ),
            Vacuna(
                nombre = "Pentavalente (1° dosis)",
                mesesDesdeNacimiento = 2,
                recibida = true
            )
            // Podés seguir agregando más...
        )
    }
}