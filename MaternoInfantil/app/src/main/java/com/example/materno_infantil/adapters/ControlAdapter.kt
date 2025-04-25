package com.example.materno_infantil.adapters

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.materno_infantil.databinding.ItemControlPendienteBinding
import android.view.ViewGroup
import com.example.materno_infantil.models.ControlPrenatal

class ControlAdapter(private val lista: List<ControlPrenatal>) :
    RecyclerView.Adapter<ControlAdapter.ControlViewHolder>() {

    inner class ControlViewHolder(val binding: ItemControlPendienteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ControlViewHolder {
        val binding = ItemControlPendienteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ControlViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ControlViewHolder, position: Int) {
        val item = lista[position]
        holder.binding.tvSemana.text = item.semana
        holder.binding.tvDescripcion.text = item.descripcion
    }

    override fun getItemCount(): Int = lista.size
}
