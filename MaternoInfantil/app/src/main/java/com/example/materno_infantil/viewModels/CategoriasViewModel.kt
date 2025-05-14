package com.example.materno_infantil.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materno_infantil.model.CategoriaConsejo
import com.example.materno_infantil.R

class CategoriasViewModel : ViewModel() {
    val items: MutableLiveData<MutableList<CategoriaConsejo>> = MutableLiveData(
        mutableListOf(
            CategoriaConsejo("Lactancia", R.drawable.lactancia1, false),
            CategoriaConsejo("Sueño seguro", R.drawable.dormir4, false),
            CategoriaConsejo("Enfermedades respiratorias", R.drawable.respiratorias2, false),
            CategoriaConsejo("Alimentación", R.drawable.alimentacion3, false),
            CategoriaConsejo("Epidemiología", R.drawable.dormir3, false)
        )
    )
}

