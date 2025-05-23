package com.example.materno_infantil.viewModels

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.materno_infantil.models.Lugar
import com.google.android.gms.location.*

class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val _currentLocation = MutableLiveData<Location?>()
    val currentLocation: LiveData<Location?> = _currentLocation

    private val _lugaresSalud = MutableLiveData<MutableList<Lugar>>()
    val lugaresSalud: LiveData<MutableList<Lugar>> = _lugaresSalud

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
    private val locationRequest = LocationRequest.create().apply {
        interval = 10000
        fastestInterval = 5000
        priority = Priority.PRIORITY_HIGH_ACCURACY
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            _currentLocation.value = result.lastLocation
        }
    }

    init {
        cargarLugaresSalud()
        iniciarActualizacionUbicacion()
    }

    fun iniciarActualizacionUbicacion() {
        if (ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {

            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null
            )

            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    _currentLocation.value = location
                }
            }
        } else {
            _currentLocation.value = null
        }
    }

    private fun cargarLugaresSalud() {
        val listaLugares = mutableListOf(
            Lugar(-42.7620, -65.0357, "Hospital Zonal Dr. Andrés R. Isola"),
            Lugar(-42.7673, -65.0377, "Sanatorio y Maternidad Santa María"),
            Lugar(-42.7718, -65.0442, "CAPS Ramón Carrillo"),
            Lugar(-42.7853, -65.0279, "CAPS Ruca Calil"),
            Lugar(-42.7538, -65.0543, "CAPS René Favaloro"),
            Lugar(-42.7758, -65.0335, "CAPS Fontana"),
            Lugar(-42.7851714,-65.0597698, "CAPS Roque González"),
            Lugar(-42.7580, -65.0415, "CAPS Primitiva Azcárate (Barrio Roca)"),
            Lugar(-42.774, -65.040, "Consultorios de la Mujer"),
            Lugar(-42.7830, -65.0370, "Centro Modular Sanitario 19"),
            Lugar(-42.764, -65.034, "Vita Medicina Reproductiva"),
            Lugar(-42.767, -65.037, "Sanatorio Y Maternidad Santa Maria"),
            Lugar(-42.767, -65.038, "Clínica Santa María"),
        )
        _lugaresSalud.value = listaLugares
    }

    override fun onCleared() {
        super.onCleared()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}
