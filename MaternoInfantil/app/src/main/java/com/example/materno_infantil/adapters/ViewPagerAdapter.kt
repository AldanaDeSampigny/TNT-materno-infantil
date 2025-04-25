package com.example.materno_infantil.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.materno_infantil.controllers.ControlMedicoFragment
import com.example.materno_infantil.controllers.ControlesRealizadosFragment
import com.example.materno_infantil.controllers.ControlesPendientesFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ControlesRealizadosFragment()
            1 -> ControlesPendientesFragment()
            2 -> ControlMedicoFragment()
            else -> throw IllegalArgumentException("Posición inválida")
        }
    }
}