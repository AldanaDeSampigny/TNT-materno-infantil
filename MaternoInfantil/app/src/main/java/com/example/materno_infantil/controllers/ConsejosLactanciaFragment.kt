package com.example.materno_infantil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materno_infantil.models.ConsejoLactancia

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
            ConsejoLactancia("Inicio temprano", "Amamanta a tu bebé tan pronto como sea posible después del nacimiento.",R.drawable.lactancia1),
            ConsejoLactancia("Buena prendida", "Asegúrate de que el bebé esté bien prendido al pecho para evitar dolor y asegurar una buena alimentación.",R.drawable.lactancia2),
            ConsejoLactancia("Lactancia a demanda", "Amamanta a demanda, es decir, cuando el bebé muestre signos de hambre.",R.drawable.lactancia3),
            ConsejoLactancia("Ambos pechos", "Ofrece ambos pechos en cada toma.",R.drawable.lactancia4),
            ConsejoLactancia("Ambiente tranquilo", "Busca un lugar tranquilo y cómodo para amamantar.",R.drawable.lactancia5),
            ConsejoLactancia("Hidratación y nutrición", "Mantén una buena hidratación y nutrición.",R.drawable.lactancia6),
            ConsejoLactancia("No compararse", "No te compares con otras mamás, cada experiencia de lactancia es única.",R.drawable.lactancia7),
            ConsejoLactancia("Buscar apoyo", "Si tienes dudas o problemas, busca apoyo de profesionales de la salud o grupos de lactancia.",R.drawable.lactancia8)
        )

        val adapter = ConsejoLactanciaAdapter(listaConsejos)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}