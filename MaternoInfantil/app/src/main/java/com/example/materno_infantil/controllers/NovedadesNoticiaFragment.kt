package com.example.materno_infantil.controllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.materno_infantil.R
import com.example.materno_infantil.adapters.NovedadesAdapter
import com.example.materno_infantil.adapters.NovedadesNoticiaAdapter
import com.example.materno_infantil.databinding.FragmentNovedadesNoticiaBinding
import com.example.materno_infantil.models.Noticia
import com.example.materno_infantil.models.NovedadesAlerta


class NovedadesNoticiaFragment : Fragment() {
    private var _binding: FragmentNovedadesNoticiaBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: NovedadesNoticiaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNovedadesNoticiaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NovedadesNoticiaAdapter(getListaNoticias())
        binding.recyclerNovedadesNoticia.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerNovedadesNoticia.adapter = adapter
    }

    private fun getListaNoticias(): List<Noticia> {
        return listOf(
            Noticia(
                titulo = "Bronquiolitis en aumento",
                imagen = R.drawable.ic_bronquiolitis_noticia,
                subtitulo = "Casos aumentaron un 15% en la región centro",
                contenido = "• Afecta a bebés menores de 1 año\n• Consultar si hay fiebre o dificultad para respirar\n• Recomendaciones: ventilar, evitar contacto con enfermos, vacunas al día"
            ),
            Noticia(
                titulo = "Dengue en zonas urbanas",
                imagen = R.drawable.ic_dengue_noticia,
                subtitulo = "Nuevos focos en barrios del norte",
                contenido = "• Usar repelente\n• Eliminar recipientes con agua estancada\n• Las embarazadas deben extremar precauciones ante picaduras\n• Consultar ante fiebre alta o dolores intensos"
            ),
            Noticia(
                titulo = "Gripe A y vacunación antigripal",
                imagen = R.drawable.ic_gripe_noticia,
                subtitulo = "Inicio de la campaña nacional de vacunación 2025",
                contenido = "• Recomendado para embarazadas en cualquier trimestre\n• Protege al recién nacido en los primeros meses\n• Vacunas disponibles en centros de salud públicos\n• Consultá tu calendario en la app"
            ),
            Noticia(
                titulo = "COVID-19: repunte leve",
                imagen = R.drawable.ic_covid_noticia,
                subtitulo = "Incremento del 8% en las últimas dos semanas",
                contenido = "• No se reportan casos graves en embarazadas\n• Se recomienda uso de barbijo en espacios cerrados\n• Lavado de manos y ventilación siguen siendo claves\n• Refuerzo disponible para personas gestantes"
            ),
            Noticia(
                titulo = "Tos convulsa en lactantes",
                imagen = R.drawable.ic_tos_convulsa,
                subtitulo = "Aumento de casos en menores de 3 meses",
                contenido = "• Se transmite fácilmente por adultos\n• La vacuna triple bacteriana acelular (dTpa) es clave en embarazo\n• Consultar si el bebé tiene tos persistente con dificultad para respirar"
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}