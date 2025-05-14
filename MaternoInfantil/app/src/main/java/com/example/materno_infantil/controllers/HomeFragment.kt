package com.example.materno_infantil.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.viewModels
import com.example.materno_infantil.R
import com.example.materno_infantil.adapters.CaruselHomeAdapter
import com.example.materno_infantil.models.CarouselItem
import com.example.materno_infantil.model.CategoriaConsejo
import com.example.materno_infantil.viewModels.CategoriasViewModel

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var caruselAdapter: CaruselHomeAdapter
    private lateinit var items: MutableList<CategoriaConsejo>
    private val categoriaViewModel: CategoriasViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCarousel)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        categoriaViewModel.items.observe(viewLifecycleOwner) { lista ->
            caruselAdapter = CaruselHomeAdapter(lista) { categoria ->
                if (categoria.nombre.lowercase() == "novedades epidemiologicas") {
                    val action = HomeFragmentDirections.actionHomeFragmentToNovedadesFragment()
                    view.findNavController().navigate(action)
                } else {
                    val action =
                        HomeFragmentDirections.actionHomeFragmentToConsejosFragment(categoria.nombre)
                    view.findNavController().navigate(action)
                }
            }
            recyclerView.adapter = caruselAdapter
        }
    }
}

