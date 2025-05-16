package com.example.materno_infantil.adapters


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materno_infantil.databinding.ItemDiaCalendarioBinding
import com.example.materno_infantil.models.ControlPrenatal
import java.time.LocalDate

class CalendarControlesAdapter(
    private val dias: List<String>,
    private val controles: Map<Int, ControlPrenatal>,
    private val diasPendientes: Set<LocalDate>,
    private val diasRealizados: Set<LocalDate>,
    private val onDiaClick: (Int) -> Unit
) : RecyclerView.Adapter<CalendarControlesAdapter.DiaViewHolder>() {

    inner class DiaViewHolder(val binding: ItemDiaCalendarioBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaViewHolder {
        val binding = ItemDiaCalendarioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiaViewHolder, position: Int) {
        val diaTexto = dias[position]
        val binding = holder.binding

        // Día vacío (del relleno inicial del calendario)
        if (diaTexto.isEmpty()) {
            binding.tvDia.text = ""
            binding.root.setBackgroundColor(Color.TRANSPARENT)
            binding.tvDia.setTextColor(Color.GRAY)
            binding.root.isClickable = false
            return
        }

        val diaInt = diaTexto.toInt()

        binding.tvDia.text = diaTexto
        binding.root.isClickable = true

        // Control asociado a ese día
        val control = controles[diaInt]
        if (control != null) {
            binding.tvDescripcion.text = control.descripcion
            binding.tvDescripcion.visibility = android.view.View.VISIBLE
        } else {
            binding.tvDescripcion.visibility = android.view.View.GONE
        }

        // Construir la fecha completa para comparar estados
        // (Asumo que el adapter recibe sólo días del mes actual, para comparar fecha necesitás año y mes)
        // Aquí hay un detalle: este adapter no tiene año y mes.
        // Lo ideal es pasarlo o calcularlo afuera y pasar un Set<LocalDate> correcto.
        // Para esta versión, simplifico y asumo que el fragment te pasa diasPendientes y diasRealizados con las fechas correctas.

        // Busco si el día está en pendientes o realizados
        val fechaDummyPendiente = diasPendientes.find { it.dayOfMonth == diaInt }
        val fechaDummyRealizado = diasRealizados.find { it.dayOfMonth == diaInt }

        // Para el color: priorizo realizados sobre pendientes
        when {
            fechaDummyRealizado != null -> {
                // Día realizado - fondo verde claro
                binding.root.setBackgroundColor(Color.parseColor("#A5D6A7")) // verde claro
                binding.tvDia.setTextColor(Color.BLACK)
            }
            fechaDummyPendiente != null -> {
                // Día pendiente - fondo naranja claro
                binding.root.setBackgroundColor(Color.parseColor("#FFCC80")) // naranja claro
                binding.tvDia.setTextColor(Color.BLACK)
            }
            else -> {
                // Día normal sin control
                binding.root.setBackgroundColor(Color.TRANSPARENT)
                binding.tvDia.setTextColor(Color.DKGRAY)
            }
        }

        // Click para alternar estado
        binding.root.setOnClickListener {
            onDiaClick(diaInt)
        }
    }

    override fun getItemCount(): Int = dias.size
}
