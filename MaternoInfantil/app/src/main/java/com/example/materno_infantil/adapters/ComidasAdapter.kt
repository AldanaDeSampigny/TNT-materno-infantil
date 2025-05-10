package com.example.materno_infantil.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materno_infantil.R
import com.example.materno_infantil.model.CategoriaConsejo
import com.example.materno_infantil.models.Comida

class ComidasAdapter(private var listaComidas: List<Comida>) :
    RecyclerView.Adapter<ComidasAdapter.ComidaViewHolder>() {

    class ComidaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.titulo)
        val descripcion: TextView = itemView.findViewById(R.id.descripcion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComidaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comida, parent, false)
        return ComidaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ComidaViewHolder, position: Int) {
        val comidaActual = listaComidas[position]
        holder.titulo.text = comidaActual.titulo
        holder.descripcion.text = comidaActual.descripcion
    }

    override fun getItemCount() = listaComidas.size

    // 👇 Función para actualizar dinámicamente la lista
    fun actualizarComidas(nuevaLista: List<Comida>) {
        listaComidas = nuevaLista
        notifyDataSetChanged()
    }
}

