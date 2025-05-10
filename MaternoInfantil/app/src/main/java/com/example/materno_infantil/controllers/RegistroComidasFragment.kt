package com.example.materno_infantil.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materno_infantil.R
import com.example.materno_infantil.adapters.CategoriaAdapter
import com.example.materno_infantil.adapters.ComidasAdapter
import com.example.materno_infantil.adapters.ConsejoAdapter
import com.example.materno_infantil.model.CategoriaConsejo
import com.example.materno_infantil.models.Comida

class RegistroComidasFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var comidasAdapter: ComidasAdapter
    private lateinit var fechaTextView: TextView
    private lateinit var botonAnterior: Button
    private lateinit var botonSiguiente: Button

    private val fechas = listOf("08/05/2025", "09/05/2025", "10/05/2025","11/05/2025")
    private var indiceActual = 0

    private val comidasPorFecha: Map<String, List<Comida>> = mapOf(
        "08/05/2025" to listOf(
            Comida("• Desayuno", "1 taza de leche descremada con café\n2 tostadas integrales con mermelada sin azúcar"),
            Comida("• Almuerzo", "Pechuga de pollo a la plancha\nEnsalada de hojas verdes con tomate y zanahoria\n1 vaso de agua"),
            Comida("• Merienda", "1 yogur natural con avena y frutas secas\n1 infusión sin azúcar"),
            Comida("• Cena", "Sopa de verduras\nTortilla de papas al horno\n1 fruta (manzana o pera)")
        ),
        "09/05/2025" to listOf(
            Comida("• Desayuno", "Yogur con cereales integrales\n1 banana"),
            Comida("• Almuerzo", "Milanesa de soja\nPuré de calabaza\nAgua saborizada natural"),
            Comida("• Merienda", "Licuado de leche con frutilla\nGalletas integrales con queso untable"),
            Comida("• Cena", "Tarta de verduras (acelga y ricota)\nEnsalada de tomate\n1 vaso de agua")
        ),
        "10/05/2025" to listOf(
            Comida("• Desayuno", "Infusión de manzanilla\nTostadas con palta y tomate\n1 fruta"),
            Comida("• Almuerzo", "Fideos integrales con salsa casera de tomate y albahaca\n1 fruta de postre"),
            Comida("• Merienda", "Pan integral con queso y mermelada\n1 té verde"),
            Comida("• Cena", "Filete de pescado al horno\nPapas al vapor con orégano\n1 yogur natural")
        ),
        "11/05/2025" to listOf(
            Comida("• Desayuno", "Infusión sin cafeína\nTostadas con queso untable y miel\n1 jugo natural de naranja"),
            Comida("• Almuerzo", "Tortilla de zapallitos\nEnsalada de arroz integral y lentejas\n1 fruta fresca"),
            Comida("• Merienda", "Batido de banana con leche\nGalletas de avena caseras"),
            Comida("• Cena", "Sopa de verduras casera\nRevuelto de espinaca y huevo\n1 vaso de leche tibia")
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_registro_comidas, container, false)

        recyclerView = rootView.findViewById(R.id.consejosLactanciaRecyclerView)
        fechaTextView = rootView.findViewById(R.id.dia)
        botonAnterior = rootView.findViewById(R.id.diaAnterior)
        botonSiguiente = rootView.findViewById(R.id.diaSiguiente)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        comidasAdapter = ComidasAdapter(emptyList())
        recyclerView.adapter = comidasAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        actualizarVista()

        botonAnterior.setOnClickListener {
            if (indiceActual > 0) {
                indiceActual--
                actualizarVista()
            }
        }

        botonSiguiente.setOnClickListener {
            if (indiceActual < fechas.size - 1) {
                indiceActual++
                actualizarVista()
            }
        }
    }

    private fun actualizarVista() {
        val fecha = fechas[indiceActual]
        fechaTextView.text = fecha
        val comidas = comidasPorFecha[fecha] ?: emptyList()
        comidasAdapter.actualizarComidas(comidas)
    }
}
