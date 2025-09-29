package com.example.juegonarrativoaventura.ui.scene

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.juegonarrativoaventura.R
import com.example.juegonarrativoaventura.model.PointOfInterest // Importar el modelo

class SceneTextFragment : Fragment() {

    // Constante para el argumento
    companion object {
        private const val ARG_POI_ID = "poi_id"
        // Factory method para crear el Fragment con argumentos
        fun newInstance(poiId: Int): SceneTextFragment {
            val fragment = SceneTextFragment()
            fragment.arguments = Bundle().apply {
                putInt(ARG_POI_ID, poiId)
            }
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scene_text, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // REQUISITO: Funcionalidad de Puntos de Interés (PdI) - Nivel 3
        val poiId = arguments?.getInt(ARG_POI_ID) ?: 0
        val poi = getPoiData(poiId)

        // Muestra la información del PdI
        view.findViewById<TextView>(R.id.tv_scene_text).text = poi.description
        // Nota: Asegúrate de que el TextView exista en fragment_scene_text.xml
    }

    // SIMULACIÓN DE DATOS DEL PdI
    private fun getPoiData(id: Int): PointOfInterest {
        return when (id) {
            1 -> PointOfInterest(
                id = 1,
                name = "Ruinas de El Dorado",
                description = "🌄 ¡Has llegado a la antigua capital! Las ruinas de El Dorado se alzan, contando historias de una civilización perdida. Este es el punto final de tu aventura en la Región.",
                imageResId = R.drawable.ic_launcher_foreground // Reemplazar con una imagen real
            )
            // Agrega más casos si tienes más escenas
            else -> PointOfInterest(0, "Error", "No se encontró el Punto de Interés", 0)
        }
    }
}