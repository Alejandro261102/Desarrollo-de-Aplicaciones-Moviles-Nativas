package com.example.juegonarrativoaventura.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.juegonarrativoaventura.R
import com.example.juegonarrativoaventura.ui.region.RegionActivity // Importar la Activity contenedora

class RegionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_region, container, false)

        // PdI PRINCIPAL - Nivel 2: Navega al Nivel 3 (SceneActivity)
        view.findViewById<Button>(R.id.btn_enter_scene).setOnClickListener {
            // ID de ejemplo para la escena/país
            (activity as? RegionActivity)?.navigateToScene(poiId = 1)
        }

        // PdI SECUNDARIO - Nivel 2: Muestra información en un Fragment dentro de la Activity
        // Simula otro punto de interés en el mapa de la región
        view.findViewById<Button>(R.id.btn_info_poi_region).setOnClickListener {
            showRegionPoiInfo()
        }

        return view
    }

    // REQUISITO: Transiciones Creativas entre Fragments
    private fun showRegionPoiInfo() {
        val choiceFragment = ChoiceFragment()
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_up, // Animación de entrada: deslizar hacia arriba
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_down // Animación de salida: deslizar hacia abajo
            )
            .replace(R.id.fragment_container_region, choiceFragment)
            .addToBackStack(null) // Permite volver con el botón atrás
            .commit()
    }
}