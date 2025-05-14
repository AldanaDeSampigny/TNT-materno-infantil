package com.example.materno_infantil.controllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.materno_infantil.R
import com.example.materno_infantil.adapters.NovedadesAdapter
import com.example.materno_infantil.databinding.FragmentNovedadesAlertasBinding
import com.example.materno_infantil.models.NovedadesAlerta


/**
 * A simple [Fragment] subclass.
 * Use the [NovedadesAlertasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NovedadesAlertasFragment : Fragment() {
    private var _binding: FragmentNovedadesAlertasBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: NovedadesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNovedadesAlertasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NovedadesAdapter(getAlertasListas())
        binding.novedadesAlerta.layoutManager = LinearLayoutManager(requireContext())
        binding.novedadesAlerta.adapter = adapter
    }

    private fun getAlertasListas(): List<NovedadesAlerta> {
        return listOf(
            NovedadesAlerta(
                titulo = "Aumento de casos de bronquiolitis en Chubut",
                contenido = "Se recomienda extremar cuidados en lactantes. Ventilación, evitar aglomeraciones y no fumar en interiores.",
                imagen = R.drawable.ic_bronquiolitis
            ),
            NovedadesAlerta(
                titulo = "Alerta por casos de dengue en Formosa",
                contenido = "Evite acumulación de agua en recipientes. Use repelente y ropa clara. Control de mosquitos en curso.",
                imagen = R.drawable.ic_dengue
            ),
            NovedadesAlerta(
                titulo = "Circulación de gripe A en Buenos Aires",
                contenido = "La provincia registra un incremento de contagios. Se recomienda vacunación y uso de barbijo en lugares cerrados.",
                imagen = R.drawable.ic_gripe
            ),
            NovedadesAlerta(
                titulo = "Recomendaciones por COVID-19 en Chubut",
                contenido = "Aumentaron los contagios leves. Se sugiere reforzar ventilación, barbijo en hospitales y completar esquema de vacunación.",
                imagen = R.drawable.ic_covid
            ),
            NovedadesAlerta(
                titulo = "Alerta amarilla por virus sincicial respiratorio en Patagonia",
                contenido = "Afecta principalmente a bebés menores de 6 meses. Consultá al médico ante signos de dificultad respiratoria.",
                imagen = R.drawable.ic_alerta_respiratoria
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}