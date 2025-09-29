package com.example.juegonarrativoaventura.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.juegonarrativoaventura.R

class ChoiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choice, container, false)
    }

    // *** LÓGICA AÑADIDA ***
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Funcionalidad del Botón 1
        view.findViewById<Button>(R.id.btn_option1).setOnClickListener {
            Toast.makeText(context, "Has elegido la Opción 1", Toast.LENGTH_SHORT).show()

            // Cierra este fragment y vuelve al anterior (RegionFragment)
            parentFragmentManager.popBackStack()
        }

        // Funcionalidad del Botón 2
        view.findViewById<Button>(R.id.btn_option2).setOnClickListener {
            Toast.makeText(context, "Has elegido la Opción 2", Toast.LENGTH_SHORT).show()

            // Cierra este fragment y vuelve al anterior (RegionFragment)
            parentFragmentManager.popBackStack()
        }
    }
}