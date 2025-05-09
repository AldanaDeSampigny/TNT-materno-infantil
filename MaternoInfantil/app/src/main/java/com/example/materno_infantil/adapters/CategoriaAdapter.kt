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
import androidx.navigation.findNavController

class CategoriaAdapter(private val listaCategorias: List<CategoriaConsejo>) :
    RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder>() {

    class CategoriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagenCategoria: ImageView = itemView.findViewById(R.id.imagen_categoria)
        val tituloCategoria: TextView = itemView.findViewById(R.id.titulo_categoria)
        val button: Button = itemView.findViewById(R.id.categoria_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_categoria, parent, false)
        return CategoriaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoriaActual = listaCategorias[position]
        holder.tituloCategoria.text = categoriaActual.nombre
        holder.imagenCategoria.setImageResource(categoriaActual.imagenResId)

           /*holder.button.setOnClickListener { view: View ->
               view.findNavController().navigate(R.id.action_categoriaConsejoFragment_to_consejosLactanciaFragment)
           }*/
    }

    override fun getItemCount() = listaCategorias.size
}