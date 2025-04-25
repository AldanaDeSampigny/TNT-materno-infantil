package com.example.materno_infantil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materno_infantil.models.ConsejoLactancia


class ConsejoLactanciaAdapter(private val consejos: List<ConsejoLactancia>) :
    RecyclerView.Adapter<ConsejoLactanciaAdapter.ConsejoViewHolder>() {

    class ConsejoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val consejoTituloTextView: TextView = itemView.findViewById(R.id.consejoTituloTextView)
        val consejoImageView: ImageView = itemView.findViewById(R.id.consejoImageView)
        val consejoTextView: TextView = itemView.findViewById(R.id.consejoTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsejoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_consejo_lactancia, parent, false)
        return ConsejoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ConsejoViewHolder, position: Int) {
        val consejoActual = consejos[position]
        holder.consejoTituloTextView.text = consejoActual.titulo
        holder.consejoTextView.text = consejoActual.texto
        holder.consejoImageView.setImageResource(consejoActual.imagenResId)
    }

    override fun getItemCount() = consejos.size
}