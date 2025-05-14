package com.example.materno_infantil.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materno_infantil.databinding.ItemNovedadesAlertaBinding
import com.example.materno_infantil.models.NovedadesAlerta

class NovedadesAdapter(private val alertas : List<NovedadesAlerta>):
    RecyclerView.Adapter<NovedadesAdapter.AlertasViewHolder>() {

    inner class AlertasViewHolder(val binding: ItemNovedadesAlertaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertasViewHolder {
        val binding = ItemNovedadesAlertaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlertasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlertasViewHolder, position: Int) {
        val item = alertas[position]
        holder.binding.imagenAlerta.setImageResource(item.imagen)
        holder.binding.titulo.text = item.titulo
        holder.binding.contenido.text = item.contenido
    }

    override fun getItemCount(): Int = alertas.size
}