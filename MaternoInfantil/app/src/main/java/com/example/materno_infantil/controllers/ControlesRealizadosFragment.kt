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
import com.example.materno_infantil.models.ControlPrenatal
import com.example.materno_infantil.models.ControlesViewModel
import java.time.LocalDate
import androidx.lifecycle.Observer
import java.time.format.DateTimeFormatter


import android.widget.Button
import android.widget.Toast
import java.io.File

import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.properties.TextAlignment
import android.os.Environment


/**
 * A simple [Fragment] subclass.
 * Use the [ControlesRealizadosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ControlesRealizadosFragment : Fragment() {

    // ►–– Se requiere para unificar los dos controles en una lista luego - sealed class
    sealed class ControlItem(val fecha: LocalDate) : Comparable<ControlItem> {
        class Medico(val data: ControlMedico) :
            ControlItem(data.fecha)           // ← fechaLocal es LocalDate dentro del modelo
        class Prenatal(val data: ControlPrenatal) :
            ControlItem(data.fechaControl)

        override fun compareTo(other: ControlItem): Int =
            other.fecha.compareTo(this.fecha)      // para ordenar DESC
    }



    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ControlesAdapter
    private val viewModel: ControlesViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_controles_realizados, container, false)
        recyclerView = view.findViewById(R.id.recyclerControles)
        adapter = ControlesAdapter(mutableListOf())

        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = adapter

        //   viewModel.controlesRealizados.observe(viewLifecycleOwner) { lista ->
     //       adapter.updateData(lista)
     //   }

        // ►–– 2. Observamos las dos listas y las fusionamos
        viewModel.controlesRealizados.observe(viewLifecycleOwner, Observer { medicos ->
            combinarListas(medicos, viewModel.controles.value)
        })
        viewModel.controles.observe(viewLifecycleOwner, Observer { prenatales ->
            combinarListas(viewModel.controlesRealizados.value, prenatales)
        })

        val btnGenerarPdf = view.findViewById<Button>(R.id.btnGenerarPdf)

        btnGenerarPdf.setOnClickListener {
            val medicos = viewModel.controlesRealizados.value ?: emptyList()
            val prenatales = viewModel.controles.value ?: emptyList()
            generarPdf(medicos, prenatales)
        }



        return view
    }

    /** Combina y actualiza el adapter */
    private fun combinarListas(
        medicos: List<ControlMedico>?,
        prenatales: List<ControlPrenatal>?
    ) {
        if (medicos == null || prenatales == null) return

        val lista = buildList {
            addAll(medicos.map { ControlItem.Medico(it) })
            addAll(prenatales.filter { it.realizado }.map { ControlItem.Prenatal(it) })
        }.sorted()                                   // por fecha DESC

        adapter.updateData(lista)

    }

    class ControlesAdapter(private val controles: MutableList<ControlItem>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private val DATE_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        override fun getItemViewType(position: Int): Int = when (controles[position]) {
            is ControlItem.Medico   -> VIEW_MEDICO
            is ControlItem.Prenatal -> VIEW_PRENATAL
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                VIEW_MEDICO -> MedicoVH(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_control_realizado, parent, false)
                )
                else -> PrenatalVH(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_control_pendiente, parent, false)
                )
            }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when (val item = controles[position]) {
                is ControlItem.Medico -> (holder as MedicoVH).bind(item.data, DATE_FMT)
                is ControlItem.Prenatal -> (holder as PrenatalVH).bind(item.data, DATE_FMT)
            }
        }

        override fun getItemCount(): Int = controles.size

        fun getData(): List<ControlItem> = controles

        fun updateData(nuevaLista: List<ControlItem>) {
            controles.clear()
            controles.addAll(nuevaLista)
            notifyDataSetChanged()
        }

        // ---------- Se define los ViewHolders para cada control-----------
        inner class MedicoVH(view: View) : RecyclerView.ViewHolder(view) {
            private val tvFecha: TextView = view.findViewById(R.id.tvFecha)
            private val tvEdad: TextView  = view.findViewById(R.id.tvEdad)
            private val tvPeso: TextView  = view.findViewById(R.id.tvPeso)
            private val tvPresion: TextView = view.findViewById(R.id.tvPresion)
            private val tvObs: TextView   = view.findViewById(R.id.tvObservaciones)

            fun bind(c: ControlMedico, fmt: DateTimeFormatter) {
                tvFecha.text = c.fecha.format(fmt)
                tvEdad.text  = "Edad gestacional: ${c.edadGestacional}"
                tvPeso.text  = "Peso: ${c.peso}"
                tvPresion.text = "Presión: ${c.presion}"
                tvObs.text   = "Observaciones: ${c.observaciones}"
            }
        }

        inner class PrenatalVH(view: View) : RecyclerView.ViewHolder(view) {
            private val tvFecha: TextView = view.findViewById(R.id.tvFechaPrenatal)
            private val tvTitulo: TextView = view.findViewById(R.id.tvNombreControl)
            private val tvDesc: TextView = view.findViewById(R.id.tvDescripcionControl)

            fun bind(c: ControlPrenatal, fmt: DateTimeFormatter) {
                tvFecha.text = c.fechaControl.format(fmt)
                tvTitulo.text = c.titulo
                tvDesc.text = c.descripcion
            }
        }

        companion object {
            private const val VIEW_MEDICO = 0
            private const val VIEW_PRENATAL = 1
        }
    } // fin de la clase ControlesAAdapter

    /** IMPLEMENTACION PARA DESCARGAR REPORTE */

    private fun generarPdf(controlesMedicos: List<ControlMedico>, controlesPrenatales: List<ControlPrenatal>) {
      //val pdfPath = File(requireContext().getExternalFilesDir(null), "controles_realizados.pdf")

        val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val pdfPath = File(downloadsDir, "controles_realizados.pdf")


        val writer = PdfWriter(pdfPath)
        val pdfDoc = PdfDocument(writer)
        val document = Document(pdfDoc)

        document.add(
            Paragraph("Reporte de Controles Realizados")
                .setFontSize(18f)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20f)
        )

        if (controlesMedicos.isNotEmpty()) {
            document.add(Paragraph("Controles Médicos").setBold().setFontSize(14f).setUnderline())
            for (control in controlesMedicos) {
                document.add(Paragraph("Fecha: ${control.fecha}"))
                document.add(Paragraph("Edad gestacional: ${control.edadGestacional}"))
                document.add(Paragraph("Peso: ${control.peso}"))
                document.add(Paragraph("Presión: ${control.presion}"))
                document.add(Paragraph("Observaciones: ${control.observaciones}"))
                document.add(Paragraph("")) // espacio entre controles
            }
        }

        val prenatalesRealizados = controlesPrenatales.filter { it.realizado }
        if (prenatalesRealizados.isNotEmpty()) {
            document.add(Paragraph("Controles Prenatales Realizados").setBold().setFontSize(14f).setUnderline().setMarginTop(15f))
            for (control in prenatalesRealizados) {
                document.add(Paragraph("Fecha: ${control.fechaControl}"))
                document.add(Paragraph("Título: ${control.titulo}"))
                document.add(Paragraph("Descripción: ${control.descripcion}"))
                document.add(Paragraph("")) // espacio entre controles
            }
        }

        document.close()

        Toast.makeText(requireContext(), "PDF generado: ${pdfPath.absolutePath}", Toast.LENGTH_LONG).show()
    }



}