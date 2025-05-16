package com.example.materno_infantil.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ControlesViewModel : ViewModel() {

    private val _controlesRealizados = MutableLiveData<MutableList<ControlMedico>>(mutableListOf())
    val controlesRealizados: LiveData<MutableList<ControlMedico>> = _controlesRealizados

    fun agregarControlMedico(control: ControlMedico) {
        _controlesRealizados.value?.add(0, control) // Agrega al inicio de la lista
        _controlesRealizados.value = _controlesRealizados.value // Trigger de actualización
    }

    private val _controlesPendientes = MutableLiveData<MutableList<ControlPrenatal>>(mutableListOf())
    val controles: LiveData<MutableList<ControlPrenatal>> = _controlesPendientes

    fun agregarControlPendiente(control: ControlPrenatal) {
        val listaActual = _controlesPendientes.value ?: mutableListOf()
        listaActual.add(control)
        _controlesPendientes.value = listaActual
    }

    // Opcional: método para actualizar control (ej. marcar realizado)
    fun actualizarControl(control: ControlPrenatal) {
        val listaActual = _controlesPendientes.value ?: return
        val index = listaActual.indexOfFirst { it.fechaControl == control.fechaControl && it.titulo == control.titulo }
        if (index != -1) {
            listaActual[index] = control
            _controlesPendientes.value = listaActual
        }
    }
}
