package com.example.juegonarrativoaventura.ui.world

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.juegonarrativoaventura.R
import com.example.juegonarrativoaventura.ui.region.ForestRegionActivity // Nueva
import com.example.juegonarrativoaventura.ui.region.MountainRegionActivity // Nueva
import com.example.juegonarrativoaventura.utils.ThemeManager

class WorldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. Aplicar el tema ANTES de todo lo demás
        ThemeManager.applyTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_world)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_world, WorldMapFragment())
                .commit()
        }
    }

    // Navegación al bosque
    fun navigateToForest() {
        val intent = Intent(this, ForestRegionActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    // Navegación a las montañas
    fun navigateToMountains() {
        val intent = Intent(this, MountainRegionActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.zoom_in, R.anim.fade_out)
    }
}