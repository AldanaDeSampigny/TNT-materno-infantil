package com.example.materno_infantil.controllers

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.materno_infantil.adapters.CalendarControlesAdapter
import com.example.materno_infantil.adapters.ControlAdapter
import com.example.materno_infantil.databinding.FragmentControlesPendientesBinding
import com.example.materno_infantil.models.ControlesViewModel
import com.example.materno_infantil.models.ControlPrenatal
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*


class ControlesPendientesFragment : Fragment() {

    private var _binding: FragmentControlesPendientesBinding? = null
    private val binding get() = _binding!!

    private var currentYear = LocalDate.now().year
    private var currentMonth = LocalDate.now().monthValue

    private val viewModel: ControlesViewModel by activityViewModels()

    private val diasPendientes = mutableSetOf<LocalDate>()
    private val diasRealizados = mutableSetOf<LocalDate>()

    private var controles: List<ControlPrenatal> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentControlesPendientesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerCalendarioControles.layoutManager = GridLayoutManager(requireContext(), 7)
        binding.recyclerControlesMes.layoutManager = LinearLayoutManager(requireContext())

        binding.btnAnteriorControles.setOnClickListener {
            currentMonth--
            if (currentMonth == 0) {
                currentMonth = 12
                currentYear--
            }
            actualizarCalendario()
        }

        binding.btnSiguienteControles.setOnClickListener {
            currentMonth++
            if (currentMonth == 13) {
                currentMonth = 1
                currentYear++
            }
            actualizarCalendario()
        }

        // Observamos la lista de controles del ViewModel para actualizar la UI al cambiar
        viewModel.controles.observe(viewLifecycleOwner, Observer { lista ->
            controles = lista
            separarDiasPorEstado()
            actualizarCalendario()
        })
    }

    private fun separarDiasPorEstado() {
        diasPendientes.clear()
        diasRealizados.clear()

        controles.forEach {
            if (it.realizado) diasRealizados.add(it.fechaControl)
            else diasPendientes.add(it.fechaControl)
        }
    }

    private fun actualizarCalendario() {
        val fechaActual = YearMonth.of(currentYear, currentMonth)
        val primerDiaMes = fechaActual.atDay(1)
        val primerDiaSemana = primerDiaMes.dayOfWeek.value % 7
        val diasEnMes = fechaActual.lengthOfMonth()

        val listaDias = mutableListOf<String>()
        repeat(primerDiaSemana) { listaDias.add("") }
        for (dia in 1..diasEnMes) listaDias.add(dia.toString())

        // Actualizar el título del mes
        binding.textMesControles.text = "${fechaActual.month.getDisplayName(TextStyle.FULL, Locale("es"))} $currentYear"

        // Filtrar controles del mes actual
        val controlesDelMes = controles.filter {
            it.fechaControl.year == currentYear && it.fechaControl.monthValue == currentMonth
        }

        // Mapeo día -> control
        val controlesMapeados = controlesDelMes.associateBy { it.fechaControl.dayOfMonth }

        // Adapter de calendario con colores y clic
        binding.recyclerCalendarioControles.adapter = CalendarControlesAdapter(
            listaDias,
            controlesMapeados,
            diasPendientes,
            diasRealizados
        ) { diaSeleccionado ->
            val fechaSeleccionada = LocalDate.of(currentYear, currentMonth, diaSeleccionado)
            val control = controles.find { it.fechaControl == fechaSeleccionada }
            if (control != null) {
                val controlActualizado = control.copy(realizado = !control.realizado)
                viewModel.actualizarControl(controlActualizado)

            }
        }

        // Adapter para lista inferior de controles pendientes únicamente
        val controlesPendientesDelMes = controlesDelMes.filter { !it.realizado }
        binding.recyclerControlesMes.adapter = ControlAdapter(controlesPendientesDelMes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
