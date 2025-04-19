package com.example.materno_infantil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materno_infantil.models.ConsejoLactanciaAdapter

class ConsejosLactanciaFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_consejos_lactancia, container, false)
        recyclerView = rootView.findViewById(R.id.consejosLactanciaRecyclerView)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listaConsejos = listOf(
            "Amamanta a tu bebé tan pronto como sea posible después del nacimiento.",
            "Asegúrate de que el bebé esté bien prendido al pecho para evitar dolor y asegurar una buena alimentación.",
            "Amamanta a demanda, es decir, cuando el bebé muestre signos de hambre.",
            "Ofrece ambos pechos en cada toma.",
            "Busca un lugar tranquilo y cómodo para amamantar.",
            "Mantén una buena hidratación y nutrición.",
            "No te compares con otras mamás, cada experiencia de lactancia es única.",
            "Si tienes dudas o problemas, busca apoyo de profesionales de la salud o grupos de lactancia."
        )

        val adapter = ConsejoLactanciaAdapter(listaConsejos)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}