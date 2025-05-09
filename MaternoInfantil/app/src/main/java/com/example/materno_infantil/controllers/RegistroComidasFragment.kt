package com.example.materno_infantil.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private lateinit var listaComidas: List<Comida>


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val rootView = inflater.inflate(R.layout.fragment_registro_comidas, container, false)
            recyclerView = rootView.findViewById(R.id.consejosLactanciaRecyclerView)
            return rootView
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            /* binding.startButton.setOnClickListener { view: View ->
                 view.findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToTriviaFragment())
             }
             */

            listaComidas= listOf(
                Comida("• Desayuno", "1 vaso de leche descremada o bebida vegetal fortificada\n" +
                        "\n" +
                        "2 tostadas integrales con queso fresco y palta\n" +
                        "\n" +
                        "1 huevo revuelto\n" +
                        "\n" +
                        "1 fruta (banana, manzana o pera)"),
                Comida("• Media Mañana", "Un puñado de frutos secos (almendras o nueces sin sal)\n" +
                        "\n" +
                        "1 yogur natural con cereales integrales o semillas de chía"),
                Comida("• Almuerzo"," Filete de pollo a la plancha o legumbres (lentejas, garbanzos)\n" +
                        "\n" +
                        "Ensalada variada con vegetales de hojas verdes, tomate y zanahoria\n" +
                        "\n" +
                        "Arroz integral o puré de papas\n" +
                        "\n" +
                        "Agua o limonada natural"),
                Comida("• Merienda", "Licuado de leche con banana\n" +
                        "\n" +
                        "1 tostada integral con ricota y miel\n" +
                        "\n" +
                        "Infusión sin cafeína (como manzanilla)"),
                Comida("• Cena", "Omelette con espinaca y queso\n" +
                        "\n" +
                        "Calabaza al horno con orégano\n" +
                        "\n" +
                        "1 vaso de leche o yogur\n" +
                        "\n" +
                        "1 fruta fresca")
            )

            val comidaAdapter = ComidasAdapter(listaComidas)
            recyclerView.adapter = comidaAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
    }
