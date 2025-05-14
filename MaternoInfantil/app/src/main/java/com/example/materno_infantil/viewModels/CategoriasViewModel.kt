package com.example.materno_infantil.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materno_infantil.model.CategoriaConsejo
import com.example.materno_infantil.R

class CategoriasViewModel : ViewModel() {
    val items: MutableLiveData<MutableList<CategoriaConsejo>> = MutableLiveData(
        mutableListOf(
            CategoriaConsejo("novedades epidemiologicas", R.drawable.epidemiologica, false),
            CategoriaConsejo("Lactancia", R.drawable.lactancia1, false),
            CategoriaConsejo("Sueño seguro", R.drawable.dormir4, false),
            CategoriaConsejo("Enfermedades Respiratorias", R.drawable.respiratorias2, false),
            CategoriaConsejo("Alimentación", R.drawable.alimentacion3, false)
        )
    )
}

