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
import com.example.materno_infantil.models.VacunaEmbarazo
import com.example.materno_infantil.models.VacunaBebe
import com.example.materno_infantil.adapters.CalendarAdapter
import com.example.materno_infantil.adapters.VacunaAdapter
import com.example.materno_infantil.models.Vacuna
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

class CalendarioVacunasFragment (private val embarazo : Boolean = true) : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var textMes: TextView
    private lateinit var btnAnterior: Button
    private lateinit var btnSiguiente: Button
    private var rootView: View? = null

    private var currentYear = LocalDate.now().year
    private var currentMonth = LocalDate.now().monthValue

    private val fum = LocalDate.of(2024, 12, 1) // ejemplo
    private lateinit var vacunasConFecha: List<Pair<Vacuna, LocalDate>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calendario_vacunas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rootView = view
        recyclerView = view.findViewById(R.id.recyclerCalendario)
        textMes = view.findViewById(R.id.textMes)
        btnAnterior = view.findViewById(R.id.btnAnterior)
        btnSiguiente = view.findViewById(R.id.btnSiguiente)

        vacunasConFecha = if (embarazo) {
            VacunaEmbarazo.getVacunas().map {
                it to fum.plusDays(it.semanasDesdeFUM!! * 7)
            }
        } else {
            VacunaBebe.getVacunas().map {
                it to fum.plusDays(it.semanasDesdeFUM!! * 7)
            }
        }

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 7)

        btnAnterior.setOnClickListener {
            currentMonth--
            if (currentMonth == 0) {
                currentMonth = 12
                currentYear--
            }
            actualizarCalendario()
        }

        btnSiguiente.setOnClickListener {
            currentMonth++
            if (currentMonth == 13) {
                currentMonth = 1
                currentYear++
            }
            actualizarCalendario()
        }

        actualizarCalendario()
    }

    private fun actualizarCalendario() {
        val fecha = YearMonth.of(currentYear, currentMonth)
        val primerDiaMes = fecha.atDay(1)
        val primerDiaSemana = primerDiaMes.dayOfWeek.value % 7 // Lunes = 1, Domingo = 7 -> % 7 → Domingo = 0

        val diasEnMes = fecha.lengthOfMonth()
        val listaDias = mutableListOf<String>()

        // Espacios vacíos antes del primer día
        for (i in 1..primerDiaSemana) {
            listaDias.add("")
        }

        for (dia in 1..diasEnMes) {
            listaDias.add(dia.toString())
        }

        textMes.text = "${fecha.month.getDisplayName(TextStyle.FULL, Locale("es"))} $currentYear"

        val diaMarcado: Map<Int, Vacuna> = vacunasConFecha
            .filter { it.second.monthValue == currentMonth && it.second.year == currentYear }
            .associate { it.second.dayOfMonth to it.first }

        val vacunasMes = vacunasConFecha.filter {
            it.second.year == currentYear && it.second.monthValue == currentMonth
        }

        val vacunaAdapter = VacunaAdapter(vacunasMes)
        val recyclerVacunas = rootView?.findViewById<RecyclerView>(R.id.recyclerVacunasMes)
        recyclerVacunas?.layoutManager = LinearLayoutManager(requireContext())
        recyclerVacunas?.adapter = vacunaAdapter

        recyclerView.adapter = CalendarAdapter(listaDias, diaMarcado)
    }
}