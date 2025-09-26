package com.example.juegonarrativoaventura.ui.region


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.juegonarrativoaventura.R
import com.example.juegonarrativoaventura.ui.scene.SceneActivity


class PoiListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_poi_list, container, false)


// Ejemplo -> abrir escena
        view.findViewById<View>(R.id.btn_open_scene).setOnClickListener {
            val intent = Intent(requireContext(), SceneActivity::class.java)
            startActivity(intent)
        }


        return view
    }
}