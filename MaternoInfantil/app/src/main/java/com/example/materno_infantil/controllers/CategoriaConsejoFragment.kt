package com.example.materno_infantil.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materno_infantil.R
import com.example.materno_infantil.adapters.CategoriaAdapter
import com.example.materno_infantil.model.CategoriaConsejo

class CategoriaConsejoFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoriaAdapter: CategoriaAdapter
    private lateinit var listaDeCategorias: List<CategoriaConsejo>

    override fun onCreateView(
        inflater: LayoutInflater, 
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categoria_consejo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* binding.startButton.setOnClickListener { view: View ->
            view.findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToTriviaFragment())
        }
        */
        recyclerView = view.findViewById(R.id.recycler_view_categorias)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        listaDeCategorias = listOf(
            CategoriaConsejo("Lactancia", R.drawable.lactancia1),
            CategoriaConsejo("Sueño seguro", R.drawable.lactancia1),
            CategoriaConsejo("Enfermedades Respiratorias", R.drawable.lactancia1),
            CategoriaConsejo("Alimentacion", R.drawable.lactancia1)
        )

        categoriaAdapter = CategoriaAdapter(listaDeCategorias)
        recyclerView.adapter = categoriaAdapter
    }
}