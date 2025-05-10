package com.example.materno_infantil.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materno_infantil.R
import com.example.materno_infantil.adapters.CaruselHomeAdapter
import com.example.materno_infantil.adapters.CarouselItem
import com.example.materno_infantil.model.CategoriaConsejo

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var caruselAdapter: CaruselHomeAdapter
    private lateinit var items: List<CategoriaConsejo>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCarousel)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        items = listOf(
            CategoriaConsejo( "Lactancia",R.drawable.lactancia1,),
            CategoriaConsejo( "Sueño seguro",R.drawable.dormir4,),
            CategoriaConsejo("Enfermedades Repiratorias",R.drawable.respiratorias2,),
            CategoriaConsejo( "Alimentacion", R.drawable.alimentacion3),
            CategoriaConsejo( "Epidemeologia",R .drawable.dormir3,),
        )


        caruselAdapter = CaruselHomeAdapter(items)
        recyclerView.adapter = caruselAdapter
    }
}

