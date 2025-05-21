package com.example.materno_infantil.controllers

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.viewModels
import com.example.materno_infantil.R
import com.example.materno_infantil.adapters.CaruselHomeAdapter
import com.example.materno_infantil.viewModels.CategoriasViewModel
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var caruselAdapter: CaruselHomeAdapter
    private val categoriaViewModel: CategoriasViewModel by viewModels()
    private lateinit var mapView: MapView
    private lateinit var myLocationOverlay: MyLocationNewOverlay
    private lateinit var userLocationMarker: Marker

    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Configuration.getInstance().load(
            requireContext(),
            androidx.preference.PreferenceManager.getDefaultSharedPreferences(requireContext())
        )
        Configuration.getInstance().userAgentValue = requireContext().packageName

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = view.findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)
        val mapController = mapView.controller
        mapController.setZoom(16.0)
        mapController.setCenter(GeoPoint(-42.7699, -65.0381)) // Coordenadas de Puerto Madryn

        recyclerView = view.findViewById(R.id.recyclerViewCarousel)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        categoriaViewModel.items.observe(viewLifecycleOwner) { lista ->
            caruselAdapter = CaruselHomeAdapter(lista) { categoria ->
                val categoriaNombre: String = categoria.nombre.toString()
                if (categoriaNombre == "Novedades Epidemiologicas") {
                    val action = HomeFragmentDirections.actionHomeFragmentToNovedadesFragment()
                    view.findNavController().navigate(action)
                } else {
                    val action = HomeFragmentDirections
                        .actionHomeFragmentToConsejosFragment(categoriaNombre)
                    view.findNavController().navigate(action)
                }
            }
            recyclerView.adapter = caruselAdapter
        }

        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            enableMyLocation()
        }
    }

    private fun enableMyLocation() {
        myLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(requireContext()), mapView)
        myLocationOverlay.enableMyLocation()
        myLocationOverlay.runOnFirstFix {
            activity?.runOnUiThread {
                val loc = myLocationOverlay.myLocation ?: return@runOnUiThread

                mapView.controller.animateTo(loc)

                if (!::userLocationMarker.isInitialized) {
                    userLocationMarker = Marker(mapView).apply {
                        position = loc
                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        icon = ContextCompat.getDrawable(requireContext(), R.drawable.location)
                        title = "Mi ubicación"
                    }
                    mapView.overlays.add(userLocationMarker)
                } else {
                    userLocationMarker.position = loc
                }
                mapView.invalidate()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableMyLocation()
            } else {
                Toast.makeText(requireContext(), "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}
