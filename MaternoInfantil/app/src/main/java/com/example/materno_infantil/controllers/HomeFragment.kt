package com.example.materno_infantil.controllers

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.view.animation.BounceInterpolator
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materno_infantil.R
import com.example.materno_infantil.adapters.CaruselHomeAdapter
import com.example.materno_infantil.viewModels.CategoriasViewModel
import com.example.materno_infantil.viewModels.LocationViewModel
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*

class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var recyclerView: RecyclerView
    private lateinit var caruselAdapter: CaruselHomeAdapter
    private var googleMap: GoogleMap? = null

    private val categoriaViewModel: CategoriasViewModel by viewModels()
    private val locationViewModel: LocationViewModel by viewModels()

    private var userMarker: Marker? = null
    private val healthMarkers = mutableListOf<Marker>()

    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private var yaMoviCamara = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        recyclerView = view.findViewById(R.id.recyclerViewCarousel)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        categoriaViewModel.items.observe(viewLifecycleOwner) { lista ->
            caruselAdapter = CaruselHomeAdapter(lista) { categoria ->
                val nombre = categoria.nombre.toString()
                val action = if (nombre == "Novedades Epidemiologicas") {
                    HomeFragmentDirections.actionHomeFragmentToNovedadesFragment()
                } else {
                    HomeFragmentDirections.actionHomeFragmentToConsejosFragment(nombre)
                }
                view.findNavController().navigate(action)
            }
            recyclerView.adapter = caruselAdapter
        }

        locationViewModel.currentLocation.observe(viewLifecycleOwner) { actualizarMarcadores() }
        locationViewModel.lugaresSalud.observe(viewLifecycleOwner) { actualizarMarcadores() }

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            locationViewModel.iniciarActualizacionUbicacion()
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap?.uiSettings?.isZoomControlsEnabled = true
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-42.7699, -65.0381), 14f))
        actualizarMarcadores()
    }

    private fun actualizarMarcadores() {
        val map = googleMap ?: return
        val ubicacion = locationViewModel.currentLocation.value
        val lugares = locationViewModel.lugaresSalud.value

        healthMarkers.forEach { it.remove() }
        healthMarkers.clear()

        ubicacion?.let {
            val latLng = LatLng(it.latitude, it.longitude)

            if (userMarker == null) {
                userMarker = map.addMarker(
                    MarkerOptions()
                        .position(latLng)
                        .title("Mi ubicación")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                )
                userMarker?.tag = "usuario"
                userMarker?.showInfoWindow()
                animarRebote(userMarker!!)
            } else {
                userMarker?.position = latLng
                animarRebote(userMarker!!)
            }

            if (!yaMoviCamara) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
                yaMoviCamara = true
            }
        }

        lugares?.forEachIndexed { index, lugar ->
            val marker = map.addMarker(
                MarkerOptions()
                    .position(LatLng(lugar.latitud, lugar.longitud))
                    .title(lugar.nombre)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
            )
            marker?.let {
                it.tag = "lugar_$index"
                animarRebote(it)
                healthMarkers.add(it)
            }
        }

        map.setOnMarkerClickListener { marker ->
            animarRebote(marker)
            marker.showInfoWindow()
            false
        }
    }

    private fun animarRebote(marker: Marker) {
        val handler = Handler()
        val start = System.currentTimeMillis()
        val duration = 500L
        val interpolator = BounceInterpolator()

        handler.post(object : Runnable {
            override fun run() {
                val elapsed = System.currentTimeMillis() - start
                val t = 1.0f - interpolator.getInterpolation(elapsed.toFloat() / duration)
                val offset = t * 0.3
                marker.setAnchor(0.5f, (1.0f + offset).toFloat())

                if (t > 0.0f) {
                    handler.postDelayed(this, 16)
                } else {
                    marker.setAnchor(0.5f, 1.0f)
                }
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED) {
                locationViewModel.iniciarActualizacionUbicacion()
            } else {
                Toast.makeText(requireContext(), "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
