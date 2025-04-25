package com.example.materno_infantil.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ControlesViewModel : ViewModel() {

    private val _controlesRealizados = MutableLiveData<MutableList<ControlMedico>>(mutableListOf())
    val controlesRealizados: LiveData<MutableList<ControlMedico>> = _controlesRealizados

    fun agregarControl(control: ControlMedico) {
        _controlesRealizados.value?.add(0, control) // Agrega al inicio de la lista
        _controlesRealizados.value = _controlesRealizados.value // Trigger de actualización
    }
}
