package com.example.uielementsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.uielementsapp.R

class ButtonsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_buttons, container, false)
        v.findViewById<View>(R.id.btn_click).setOnClickListener {
            android.widget.Toast.makeText(requireContext(), "Botón presionado", android.widget.Toast.LENGTH_SHORT).show()
        }
        v.findViewById<View>(R.id.img_btn).setOnClickListener {
            android.widget.Toast.makeText(requireContext(), "ImageButton: ¡clic en el ícono!", android.widget.Toast.LENGTH_SHORT).show()
        }
        v.findViewById<View>(R.id.fab_go_list).setOnClickListener {
            // Navega al fragment de Lista usando el BottomNavigationView de la Activity
            (requireActivity() as com.example.uielementsapp.MainActivity).selectTab(R.id.nav_list)
        }
        return v
    }
}

