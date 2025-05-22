package com.example.materno_infantil.viewModels

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.materno_infantil.models.Lugar

class LocationViewModel(application: Application) : AndroidViewModel(application), LocationListener {
    private val _currentLocation = MutableLiveData<Location?>()
    val currentLocation: LiveData<Location?> = _currentLocation

    private val _lugaresSalud = MutableLiveData<MutableList<Lugar>>() // MutableLiveData de MutableList
    val lugaresSalud: LiveData<MutableList<Lugar>> = _lugaresSalud

    private val locationManager =
        application.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    init {
        startLocationUpdates()
        cargarLugaresSalud()
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(
                getApplication(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    0f,
                    this
                )
            } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    0,
                    0f,
                    this
                )
            } else {
                _currentLocation.value = null
            }
            val lastKnownLocationGPS =
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            val lastKnownLocationNetwork =
                locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

            if (lastKnownLocationGPS != null && lastKnownLocationNetwork != null) {
                _currentLocation.value =
                    if (lastKnownLocationGPS.time > lastKnownLocationNetwork.time) {
                        lastKnownLocationGPS
                    } else {
                        lastKnownLocationNetwork
                    }
            } else {
                _currentLocation.value = lastKnownLocationGPS ?: lastKnownLocationNetwork
            }
        } else {
            _currentLocation.value = null
        }
    }

    private fun cargarLugaresSalud() {
        val listaLugares = mutableListOf( // Creamos una MutableList directamente
            Lugar(-42.7620, -65.0357, "Hospital Zonal Dr. Andrés R. Isola"),
            Lugar(-42.7673, -65.0377, "Sanatorio y Maternidad Santa María"),
            Lugar(-42.7718, -65.0442, "CAPS Ramón Carrillo"),
            Lugar(-42.7853, -65.0279, "CAPS Ruca Calil"),
            Lugar(-42.7538, -65.0543, "CAPS René Favaloro"),
            Lugar(-42.7758, -65.0335, "CAPS Fontana"),
            Lugar(-42.7851714,-65.0597698, "CAPS Roque González"),
            Lugar(-42.7580, -65.0415, "CAPS Primitiva Azcárate (Barrio Roca)"),
            Lugar(-42.774, -65.040, "Consultorios de la Mujer"),
            Lugar(-42.783, -65.050, "Centro Modular Sanitario 19 - Puerto Madryn"),
            Lugar(-42.764, -65.034, "Vita Medicina Reproductiva"),
            Lugar(-42.767, -65.037, "Sanatorio Y Maternidad Santa Maria"),
            Lugar(-42.767, -65.038, "Clínica Santa María"),
        )
        _lugaresSalud.value = listaLugares
    }

    fun agregarLugarSalud(nuevoLugar: Lugar) {
        val listaActual = _lugaresSalud.value ?: mutableListOf()
        listaActual.add(nuevoLugar)
        _lugaresSalud.value = listaActual
    }

    fun removerLugarSalud(nombreLugar: String) {
        val listaActual = _lugaresSalud.value ?: mutableListOf()
        listaActual.removeAll { it.nombre == nombreLugar }
        _lugaresSalud.value = listaActual
    }

    override fun onLocationChanged(location: Location) {
        _currentLocation.value = location
    }

    override fun onProviderDisabled(provider: String) {
        // ...
    }

    override fun onProviderEnabled(provider: String) {
        // ...
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        // ...
    }

    override fun onCleared() {
        super.onCleared()
        locationManager.removeUpdates(this)
    }
}