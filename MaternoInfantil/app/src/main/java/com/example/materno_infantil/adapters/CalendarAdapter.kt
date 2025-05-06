package com.example.materno_infantil.adapters

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materno_infantil.R
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.example.materno_infantil.models.Vacuna
import android.widget.Toast
import java.time.LocalDate

class CalendarAdapter(private val dias: List<String>, private val diasMarcados: Map<Int, Vacuna>,
                      private val diasVacunados: Set<LocalDate>) :
    RecyclerView.Adapter<CalendarAdapter.DiaViewHolder>() {

    inner class DiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textDia: TextView = itemView.findViewById(R.id.textDia)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dia, parent, false)
        return DiaViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiaViewHolder, position: Int) {
        val diaTexto = dias[position]
        holder.textDia.text = diaTexto

        val indicadorVacuna = holder.itemView.findViewById<View>(R.id.indicador_vacuna)

        if (diaTexto != null && diasMarcados.containsKey(diaTexto.toIntOrNull())) {
            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.purple_200)
            )

            val dia = diaTexto.toIntOrNull()
            val fecha = LocalDate.of(LocalDate.now().year, LocalDate.now().month, dia!!)
            if (diasVacunados.contains(fecha)) {
                indicadorVacuna.visibility = View.VISIBLE
            } else {
                indicadorVacuna.visibility = View.INVISIBLE
            }

            holder.itemView.setOnClickListener {
                val vacuna = diasMarcados[diaTexto.toIntOrNull()]
                vacuna?.let {
                    Toast.makeText(
                        holder.itemView.context,
                        "Vacuna: ${it.nombre}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT)
            holder.itemView.setOnClickListener(null)
        }
    }

    override fun getItemCount(): Int = dias.size
}