package com.example.juegonarrativoaventura.ui.region


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.juegonarrativoaventura.R


class RegionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_region)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_region, PoiListFragment())
                .commit()
        }
    }
}