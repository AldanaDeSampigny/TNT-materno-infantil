package com.example.materno_infantil.models

object VacunaEmbarazo {

    fun getVacunas(): List<Vacuna>{
        return listOf(
            Vacuna(nombre = "Triple bacteriana acelular",
                semanasDesdeFUM = 20,
                recibida = true),
            Vacuna(nombre = "extra1",
                semanasDesdeFUM = 21,
                recibida = false),
            Vacuna(nombre = "extra2",
                semanasDesdeFUM = 22,
                recibida = false),
            Vacuna(nombre = "extra3",
                semanasDesdeFUM = 23,
                recibida = true),
            Vacuna(nombre = "Virus Sincicial respiratorio (VSR)",
                semanasDesdeFUM = 34,
                recibida = false)
        )
    }
}