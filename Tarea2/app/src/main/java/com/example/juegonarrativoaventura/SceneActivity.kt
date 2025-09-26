package com.example.juegonarrativoaventura.ui.scene


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.juegonarrativoaventura.R


class SceneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_scene, SceneTextFragment())
                .commit()
        }
    }
}