package com.example.materno_infantil.controllers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.materno_infantil.R
import android.content.Intent
import android.widget.Toast
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.FirebaseAuth


class SettingsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { result ->
        val response = result.idpResponse
        val user = FirebaseAuth.getInstance().currentUser
        if (result.resultCode == AppCompatActivity.RESULT_OK && user != null) {
            Log.d("ProfileFragment", "Usuario logueado: ${user.uid}")
        } else {
            Log.e("ProfileFragment", "Login cancelado o fallido", response?.error)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonRegister = view.findViewById<Button>(R.id.btn_login)
        val user = FirebaseAuth.getInstance().currentUser

        // se muestra el botón solo si el usuario es anónimo
        if (user?.isAnonymous == true) {
            buttonRegister.visibility = View.VISIBLE
            buttonRegister.setOnClickListener {
                startActivity(Intent(requireContext(), RegisterActivity::class.java))
            }
        } else {
            // Si no es anónimo, se oculta el botón
            buttonRegister.visibility = View.GONE
        }
    }

}