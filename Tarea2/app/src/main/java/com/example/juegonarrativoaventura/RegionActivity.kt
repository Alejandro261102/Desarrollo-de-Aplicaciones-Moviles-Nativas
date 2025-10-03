package com.example.juegonarrativoaventura.ui.region

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.juegonarrativoaventura.R
import com.example.juegonarrativoaventura.fragments.RegionFragment // Importación necesaria
import com.example.juegonarrativoaventura.ui.scene.SceneActivity
import com.example.juegonarrativoaventura.utils.ThemeManager

class RegionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeManager.applyTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_region)

        if (savedInstanceState == null) {
            // Carga el Fragment principal del Nivel 2
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_region, RegionFragment())
                .commit()
        }
    }

    // MeTODO DE NAVEGACIÓN con Transición Creativa a Nivel 3
    fun navigateToScene(poiId: Int) {
        val intent = Intent(this, SceneActivity::class.java).apply {
            putExtra("POI_ID", poiId) // Pasar el ID del PdI
        }
        startActivity(intent)
        // REQUISITO: Transiciones Creativas (Efecto Zoom)
        overridePendingTransition(R.anim.zoom_in, R.anim.fade_out)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}