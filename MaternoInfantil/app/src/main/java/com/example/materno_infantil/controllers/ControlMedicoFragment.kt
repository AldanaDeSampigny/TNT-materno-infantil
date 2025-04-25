package com.example.materno_infantil.controllers

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.materno_infantil.databinding.FragmentControlMedicoBinding
import com.example.materno_infantil.models.ControlMedico
import com.example.materno_infantil.models.ControlesViewModel
import java.util.Calendar


/**
 * A simple [Fragment] subclass.
 * Use the [ControlMedicoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ControlMedicoFragment : Fragment() {

    private lateinit var binding: FragmentControlMedicoBinding
    private val viewModel: ControlesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentControlMedicoBinding.inflate(inflater, container, false)

        // Configurar DatePicker
        binding.etFecha.setOnClickListener {
            mostrarDatePicker()
        }

        binding.btnGuardar.setOnClickListener {
            val fecha = binding.etFecha.text.toString()
            val edadGestacional = binding.etEdadGestacional.text.toString()
            val peso = binding.etPeso.text.toString()
            val presion = binding.etPresion.text.toString()
            val observaciones = binding.etObservaciones.text.toString()

            if (fecha.isBlank() || edadGestacional.isBlank()) {
                Toast.makeText(context, "Por favor completa los campos obligatorios", Toast.LENGTH_SHORT).show()
            } else {
                // Lógica para guardar datos (por ahora solo muestra mensaje)
                //Toast.makeText(context, "Control médico guardado", Toast.LENGTH_SHORT).show()
                val nuevoControl = ControlMedico(fecha, edadGestacional, peso, presion, observaciones)
                viewModel.agregarControl(nuevoControl)

                Toast.makeText(context, "Control médico guardado", Toast.LENGTH_SHORT).show()
                binding.etFecha.text.clear()
                binding.etEdadGestacional.text.clear()
                binding.etPeso.text.clear()
                binding.etPresion.text.clear()
                binding.etObservaciones.text.clear()
            }
        }

        return binding.root
    }

    private fun mostrarDatePicker() {
        val calendario = Calendar.getInstance()
        val year = calendario.get(Calendar.YEAR)
        val month = calendario.get(Calendar.MONTH)
        val day = calendario.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(requireContext(), { _, y, m, d ->
            val fechaSeleccionada = String.format("%02d/%02d/%04d", d, m + 1, y)
            binding.etFecha.setText(fechaSeleccionada)
        }, year, month, day)

        datePicker.show()
    }
}