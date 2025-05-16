package com.example.materno_infantil.controllers

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.materno_infantil.databinding.FragmentControlMedicoBinding
import com.example.materno_infantil.models.ControlMedico
import com.example.materno_infantil.models.ControlPrenatal
import com.example.materno_infantil.models.ControlesViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ControlMedicoFragment : Fragment() {

    private lateinit var binding: FragmentControlMedicoBinding
    private val viewModel: ControlesViewModel by activityViewModels()
    private var fechaSeleccionada: LocalDate? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentControlMedicoBinding.inflate(inflater, container, false)

        // Mostrar date picker
        binding.etFecha.setOnClickListener { mostrarDatePicker() }

        // Cambiar entre controles prenatales y médicos
        binding.radioGroupTipo.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == binding.radioPrenatal.id) {
                binding.layoutPrenatal.visibility = View.VISIBLE
                binding.layoutMedico.visibility = View.GONE
            } else {
                binding.layoutPrenatal.visibility = View.GONE
                binding.layoutMedico.visibility = View.VISIBLE
            }
        }

        // Guardar el control
        binding.btnGuardar.setOnClickListener {
            val fecha = fechaSeleccionada
            if (fecha == null) {
                Toast.makeText(requireContext(), "Selecciona una fecha", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.radioPrenatal.isChecked) {
                val titulo = binding.etTitulo.text.toString()
                val descripcion = binding.etDescripcion.text.toString()
                if (titulo.isBlank() || descripcion.isBlank()) {
                    Toast.makeText(requireContext(), "Completa todos los campos prenatales", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val nuevoControl = ControlPrenatal(titulo, descripcion, fecha, realizado = false)
                viewModel.agregarControlPendiente(nuevoControl)
                Toast.makeText(requireContext(), "Control prenatal guardado", Toast.LENGTH_SHORT).show()

                binding.etTitulo.text.clear()
                binding.etDescripcion.text.clear()
            } else {
                val edadGestacional = binding.etEdadGestacional.text.toString()
                val peso = binding.etPeso.text.toString()
                val presion = binding.etPresion.text.toString()
                val observaciones = binding.etObservaciones.text.toString()
                if (edadGestacional.isBlank()) {
                    Toast.makeText(requireContext(), "La edad gestacional es obligatoria", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val controlMedico = ControlMedico(
                    fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    edadGestacional,
                    peso,
                    presion,
                    observaciones
                )
                viewModel.agregarControlMedico(controlMedico)
                Toast.makeText(requireContext(), "Control médico guardado", Toast.LENGTH_SHORT).show()

                binding.etEdadGestacional.text.clear()
                binding.etPeso.text.clear()
                binding.etPresion.text.clear()
                binding.etObservaciones.text.clear()
            }

            // Limpiar fecha
            binding.etFecha.text.clear()
            fechaSeleccionada = null
        }

        return binding.root
    }

    private fun mostrarDatePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                fechaSeleccionada = LocalDate.of(year, month + 1, dayOfMonth)
                binding.etFecha.setText(fechaSeleccionada?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }
}
