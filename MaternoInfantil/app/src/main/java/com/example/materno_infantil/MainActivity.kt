package com.example.materno_infantil

import com.example.materno_infantil.actividades.Vacunacion
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import com.example.materno_infantil.actividades.ControlMedico
import com.example.materno_infantil.actividades.Lactancia

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val iconVacunacion = findViewById<ImageView>(R.id.vacunacion)
        val iconControl = findViewById<ImageView>(R.id.control)
        val iconLactancia = findViewById<ImageView>(R.id.lactancia)

        iconVacunacion.setOnClickListener{
            val tocar = Intent(this, Vacunacion::class.java)
            startActivity(tocar)
        }

        iconControl.setOnClickListener{
            val tocar = Intent(this, ControlMedico::class.java)
            startActivity(tocar)
        }

        iconLactancia.setOnClickListener{
            val tocar = Intent(this, Lactancia::class.java)
            startActivity(tocar)
        }
    }
}