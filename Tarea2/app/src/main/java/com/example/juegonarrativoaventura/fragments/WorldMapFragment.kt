package com.example.juegonarrativoaventura.ui.world


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.juegonarrativoaventura.R
import com.example.juegonarrativoaventura.ui.region.RegionActivity


class WorldMapFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_world_map, container, false)


// Ejemplo de click -> ir a RegionActivity
        view.findViewById<View>(R.id.btn_open_region).setOnClickListener {
            val intent = Intent(requireContext(), RegionActivity::class.java)
            startActivity(intent)
        }


        return view
    }
}