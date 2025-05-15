package com.example.materno_infantil.controllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.materno_infantil.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.materno_infantil.adapters.NovedadesAdapter
import com.example.materno_infantil.databinding.FragmentNovedadesPrevencionBinding
import com.example.materno_infantil.models.NovedadesAlerta

class NovedadesPrevencionFragment : Fragment() {
    private var _binding: FragmentNovedadesPrevencionBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: NovedadesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNovedadesPrevencionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NovedadesAdapter(getPrevencionListas())
        binding.novedadesPrevencion.layoutManager = LinearLayoutManager(requireContext())
        binding.novedadesPrevencion.adapter = adapter
    }

    private fun getPrevencionListas(): List<NovedadesAlerta> {
        return listOf(
            NovedadesAlerta(
                titulo = "Lavado de manos frecuente",
                contenido = "Antes de tocar al bebé o sus objetos, se debe lavar las manos con agua y jabón durante al menos 20 segundos.",
                imagen = R.drawable.lavado_manos
            ),
            NovedadesAlerta(
                titulo = "Ventilación diaria de los ambientes",
                contenido = "Asegurá la ventilación cruzada en las habitaciones al menos dos veces al día, incluso en invierno.",
                imagen = R.drawable.ventilacion
            ),
            NovedadesAlerta(
                titulo = "Uso de barbijo en visitas",
                contenido = "Solicitá el uso de barbijo a quienes visiten al bebé, especialmente en épocas de circulación de virus.",
                imagen = R.drawable.barbijo_visitas
            ),
            NovedadesAlerta(
                titulo = "Higiene de juguetes y chupetes",
                contenido = "Limpialos y desinfectalos regularmente, especialmente si caen al suelo o son compartidos.",
                imagen = R.drawable.juguetes_bebe
            ),
            NovedadesAlerta(
                titulo = "Evitar visitas en los primeros días",
                contenido = "Durante las primeras semanas, limitá las visitas y evitá el contacto con personas con síntomas.",
                imagen = R.drawable.visitas_neonato
            ),
            NovedadesAlerta(
                titulo = "Evitar el humo del cigarrillo",
                contenido = "El humo afecta los pulmones del bebé; mantené el hogar libre de humo de tabaco y aerosoles.",
                imagen = R.drawable.no_fumar
            ),
            NovedadesAlerta(
                titulo = "Control de temperatura del ambiente",
                contenido = "Evitá sobreabrigar al bebé. Mantené una temperatura templada (20-22°C) y sin corrientes frías.",
                imagen = R.drawable.temperatura_bebe
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}