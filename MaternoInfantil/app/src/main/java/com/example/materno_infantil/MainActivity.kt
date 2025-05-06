package com.example.materno_infantil


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.materno_infantil.controllers.HomeFragment
import com.example.materno_infantil.controllers.ControlesFragment
import com.example.materno_infantil.controllers.SettingsFragment
import com.example.materno_infantil.controllers.CategoriaConsejoFragment
import com.example.materno_infantil.controllers.CalendarioVacunasFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Fragment inicial
        loadFragment(HomeFragment())

        // Listener para los ítems
        navView.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_control -> ControlesFragment()
                R.id.nav_consejos -> ConsejosFragment() // <--- Ahora carga este Fragment
                R.id.nav_settings -> SettingsFragment()
                R.id.nav_vacunacion -> CalendarioVacunasFragment()
                else -> HomeFragment()
            }
            loadFragment(fragment)
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
