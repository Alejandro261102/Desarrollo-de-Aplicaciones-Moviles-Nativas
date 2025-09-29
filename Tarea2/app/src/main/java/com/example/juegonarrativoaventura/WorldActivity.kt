package com.example.juegonarrativoaventura.ui.world

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.juegonarrativoaventura.R
import com.example.juegonarrativoaventura.ui.region.RegionActivity // Importación necesaria

class WorldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Asegúrate de que R.layout.activity_world sea un FrameLayout contenedor
        setContentView(R.layout.activity_world)

        if (savedInstanceState == null) {
            // Carga el Fragment del mapa mundial (Nivel 1)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_world, WorldMapFragment())
                .commit()
        }
    }

    // METODO DE NAVEGACIÓN con Transición Creativa a Nivel 2
    fun navigateToRegion() {
        val intent = Intent(this, RegionActivity::class.java)
        startActivity(intent)
        // REQUISITO: Transiciones Creativas (Deslizamiento)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}