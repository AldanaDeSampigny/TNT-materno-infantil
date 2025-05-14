package com.example.materno_infantil.controllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.materno_infantil.adapters.PagerNovedadesAdapter
import com.example.materno_infantil.databinding.FragmentNovedadesBinding
import com.google.android.material.tabs.TabLayoutMediator


class NovedadesFragment : Fragment() {
    private lateinit var binding: FragmentNovedadesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNovedadesBinding.inflate(inflater, container, false)

        val adapter = PagerNovedadesAdapter(this)
        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout2, binding.viewPager2) { tab, position ->
            tab.text = when (position) {
                0 -> "Noticias"
                1 -> "Alertas"
                2 -> "Prevenciones"
                else -> ""
            }
        }.attach()

        return binding.root
    }


}