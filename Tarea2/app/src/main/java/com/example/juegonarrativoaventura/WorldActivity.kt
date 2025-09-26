package com.example.juegonarrativoaventura.ui.world


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.juegonarrativoaventura.R


class WorldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_world)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_world, WorldMapFragment())
                .commit()
        }
    }
}