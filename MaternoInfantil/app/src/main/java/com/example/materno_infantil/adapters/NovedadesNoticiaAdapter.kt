package com.example.materno_infantil.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materno_infantil.databinding.ItemNovedadesNoticiasBinding
import com.example.materno_infantil.models.Noticia

class NovedadesNoticiaAdapter(private val lista: List<Noticia>) :
        RecyclerView.Adapter<NovedadesNoticiaAdapter.NovedadNoticiaViewHolder>() {

    inner class NovedadNoticiaViewHolder(val binding: ItemNovedadesNoticiasBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NovedadNoticiaViewHolder {
        val binding = ItemNovedadesNoticiasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NovedadNoticiaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NovedadNoticiaViewHolder, position: Int) {
        val item = lista[position]
        holder.binding.TituloN.text = item.titulo
        holder.binding.imgNovedadN.setImageResource(item.imagen)
        holder.binding.SubtituloN.text = item.subtitulo
        holder.binding.ContenidoN.text = item.contenido
    }

    override fun getItemCount(): Int = lista.size
}