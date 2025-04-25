package com.example.materno_infantil.controllers


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.materno_infantil.databinding.FragmentControlesBinding
import com.example.materno_infantil.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.example.materno_infantil.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ControlesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ControlesFragment : Fragment() {
    private lateinit var binding: FragmentControlesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentControlesBinding.inflate(inflater, container, false)

        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Realizados"
                1 -> "Pendientes"
                2 -> "Nuevo"
                else -> ""
            }
        }.attach()

        return binding.root
    }
}