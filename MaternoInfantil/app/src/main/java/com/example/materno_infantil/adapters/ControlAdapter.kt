package com.example.materno_infantil.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materno_infantil.databinding.ItemControlPendienteBinding
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
        val control = lista[position]
        holder.binding.tvNombreControl.text = control.titulo
        holder.binding.tvDescripcionControl.text = control.descripcion
    }

    override fun getItemCount(): Int = lista.size
}
