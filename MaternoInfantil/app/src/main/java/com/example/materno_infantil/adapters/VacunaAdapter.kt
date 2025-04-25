package com.example.materno_infantil.adapters

import com.example.materno_infantil.models.Vacuna
import java.time.LocalDate
import androidx.recyclerview.widget.RecyclerView
import com.example.materno_infantil.R
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.CheckBox
import java.time.format.DateTimeFormatter

class VacunaAdapter (private val vacunas: List<Pair<Vacuna, LocalDate>>) :
    RecyclerView.Adapter<VacunaAdapter.VacunaViewHolder>() {

    inner class VacunaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textNombre: TextView = itemView.findViewById(R.id.textNombreVacuna)
        val textFecha: TextView = itemView.findViewById(R.id.textFechaVacuna)
        val check: CheckBox = itemView.findViewById(R.id.checkRecibida)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacunaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vacuna, parent, false)
        return VacunaViewHolder(view)
    }

    override fun onBindViewHolder(holder: VacunaViewHolder, position: Int) {
        val (vacuna, fecha) = vacunas[position]
        holder.textNombre.text = vacuna.nombre
        holder.textFecha.text = "Fecha: ${fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}"
        holder.check.isChecked = vacuna.recibida
    }

    override fun getItemCount(): Int = vacunas.size
}