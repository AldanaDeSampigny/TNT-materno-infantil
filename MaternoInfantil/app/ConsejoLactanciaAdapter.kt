package com.example.materno_infantil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ConsejoLactanciaAdapter(private val consejos: List<String>) :
    RecyclerView.Adapter<ConsejoLactanciaAdapter.ConsejoViewHolder>() {

    class ConsejoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val consejoTextView: TextView = itemView.findViewById(R.id.consejoTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsejoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_consejo_lactancia, parent, false)
        return ConsejoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ConsejoViewHolder, position: Int) {
        val consejoActual = consejos[position]
        holder.consejoTextView.text = consejoActual
    }

    override fun getItemCount() = consejos.size
}