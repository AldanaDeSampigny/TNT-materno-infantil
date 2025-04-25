package com.example.materno_infantil.controllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.materno_infantil.R
import com.example.materno_infantil.models.ControlMedico
import com.example.materno_infantil.models.ControlesViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ControlesRealizadosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ControlesRealizadosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ControlesAdapter
    private val viewModel: ControlesViewModel by activityViewModels()

    private val listaControles = listOf(
        ControlMedico("10/03/2025", "32 semanas", "65 kg", "120/80", "Sin observaciones"),
        ControlMedico("25/03/2025", "34 semanas", "66 kg", "115/75", "Control normal")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_controles_realizados, container, false)
        recyclerView = view.findViewById(R.id.recyclerControles)
        adapter = ControlesAdapter(mutableListOf())

        recyclerView.layoutManager = LinearLayoutManager(context)
       // recyclerView.adapter = ControlesAdapter(listaControles)
        recyclerView.adapter = adapter

        viewModel.controlesRealizados.observe(viewLifecycleOwner) { lista ->
            adapter.updateData(lista)
        }

        return view
    }


    class ControlesAdapter(private val controles: MutableList<ControlMedico>) :
        RecyclerView.Adapter<ControlesAdapter.ControlViewHolder>() {

        inner class ControlViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvFecha: TextView = view.findViewById(R.id.tvFecha)
            val tvEdad: TextView = view.findViewById(R.id.tvEdad)
            val tvPeso: TextView = view.findViewById(R.id.tvPeso)
            val tvPresion: TextView = view.findViewById(R.id.tvPresion)
            val tvObservaciones: TextView = view.findViewById(R.id.tvObservaciones)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ControlViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_control_realizado, parent, false)
            return ControlViewHolder(view)
        }

        override fun onBindViewHolder(holder: ControlViewHolder, position: Int) {
            val control = controles[position]
            holder.tvFecha.text = control.fecha
            holder.tvEdad.text = "Edad gestacional: ${control.edadGestacional}"
            holder.tvPeso.text = "Peso: ${control.peso}"
            holder.tvPresion.text = "Presión: ${control.presion}"
            holder.tvObservaciones.text = "Observaciones: ${control.observaciones}"
        }

        override fun getItemCount(): Int = controles.size

        fun updateData(nuevaLista: List<ControlMedico>) {
            controles.clear()
            controles.addAll(nuevaLista)
            notifyDataSetChanged()
        }
    }
}