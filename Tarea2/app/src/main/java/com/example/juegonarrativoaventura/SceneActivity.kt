package com.example.juegonarrativoaventura.ui.scene

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.juegonarrativoaventura.R

class SceneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene)

        // Obtiene el ID del PdI para cargar la información correcta
        val poiId = intent.getIntExtra("POI_ID", 0)

        if (savedInstanceState == null) {
            // Pasa el poiId al Fragment
            val fragment = SceneTextFragment.newInstance(poiId)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_scene, fragment)
                .commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Transición de regreso (Inversa a la entrada)
        overridePendingTransition(R.anim.fade_in, R.anim.zoom_out)
    }
}