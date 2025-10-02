package com.example.juegonarrativoaventura.ui.world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.example.juegonarrativoaventura.R
import com.example.juegonarrativoaventura.utils.ThemeManager

class WorldMapFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_world, container, false)

        // --- Lógica del Switch de Tema ---
        val themeSwitch = view.findViewById<SwitchCompat>(R.id.switch_theme)

        // Establecer el estado inicial del switch
        themeSwitch.isChecked = ThemeManager.isDarkMode(requireContext())

        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Guardar la preferencia
            ThemeManager.saveThemePreference(requireContext(), isChecked)
            // Reiniciar la actividad para aplicar el tema
            activity?.recreate()
        }

        // --- Lógica de Navegación ---
        view.findViewById<Button>(R.id.btn_to_forest).setOnClickListener {
            (activity as? WorldActivity)?.navigateToForest()
        }

        view.findViewById<Button>(R.id.btn_to_mountains).setOnClickListener {
            (activity as? WorldActivity)?.navigateToMountains()
        }

        return view
    }
}