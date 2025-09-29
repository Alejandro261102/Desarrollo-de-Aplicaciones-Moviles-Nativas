package com.example.juegonarrativoaventura.ui.world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button // Importación necesaria
import androidx.fragment.app.Fragment
import com.example.juegonarrativoaventura.R // Importación necesaria

class WorldMapFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout que realmente existe
        val view = inflater.inflate(R.layout.fragment_world, container, false)

        // PdI Nivel 1: Transición a Activity Nivel 2
        // El ID debe ser el que está en fragment_world.xml
        view.findViewById<Button>(R.id.btn_enter_region).setOnClickListener {
            // Llama al metodo de WorldActivity para aplicar la transición creativa.
            (activity as? WorldActivity)?.navigateToRegion()
        }

        return view
    }
}