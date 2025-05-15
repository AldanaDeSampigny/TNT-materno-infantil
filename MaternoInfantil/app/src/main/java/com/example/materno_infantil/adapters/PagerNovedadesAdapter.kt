package com.example.materno_infantil.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.materno_infantil.controllers.NovedadesAlertasFragment
import com.example.materno_infantil.controllers.NovedadesNoticiaFragment
import com.example.materno_infantil.controllers.NovedadesPrevencionFragment

class PagerNovedadesAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NovedadesNoticiaFragment()
            1 -> NovedadesAlertasFragment()
            2 -> NovedadesPrevencionFragment()
            else -> throw IllegalArgumentException("Posición inválida")
        }
    }
}