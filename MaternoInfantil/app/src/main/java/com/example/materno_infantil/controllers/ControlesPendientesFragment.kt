package com.example.materno_infantil.controllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.materno_infantil.adapters.ControlAdapter
import com.example.materno_infantil.databinding.FragmentControlesPendientesBinding
import com.example.materno_infantil.models.ControlPrenatal
import androidx.recyclerview.widget.LinearLayoutManager



/**
 * A simple [Fragment] subclass.
 * Use the [ControlesPendientesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ControlesPendientesFragment : Fragment() {
    private var _binding: FragmentControlesPendientesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ControlAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentControlesPendientesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ControlAdapter(getListaControles())
        binding.rvControles.layoutManager = LinearLayoutManager(requireContext())
        binding.rvControles.adapter = adapter
    }

    private fun getListaControles(): List<ControlPrenatal> {
        return listOf(
            ControlPrenatal("Semana 6-8", """
            • Primera consulta
            • Análisis de sangre
            • Análisis de orina
            • Ecografía inicial
        """.trimIndent()),

            ControlPrenatal("Semana 12", """
            • Confirmación de edad gestacional
            • Detección de malformaciones
        """.trimIndent()),

            ControlPrenatal("Semana 13-16", """
            • Control prenatal (peso, presión, altura uterina, latido fetal)
            • Resultado de estudios anteriores
            • Consejos de alimentación y actividad física
        """.trimIndent()),

            ControlPrenatal("Semana 20", """
            • Ecografía morfológica
            • Control del desarrollo fetal
        """.trimIndent()),

            ControlPrenatal("Semana 24-28", """
            • Test de glucosa
            • Control de anemia
        """.trimIndent()),

            ControlPrenatal("Semana 35-37", """
            • Hisopado para estreptococo grupo B
        """.trimIndent()),

            ControlPrenatal("Semana 37+", """
            • Controles semanales
            • Monitoreos fetales si es necesario
        """.trimIndent())
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}