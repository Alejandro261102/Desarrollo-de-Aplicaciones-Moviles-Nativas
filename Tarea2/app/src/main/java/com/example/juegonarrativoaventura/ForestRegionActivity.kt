package com.example.juegonarrativoaventura.ui.region

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.juegonarrativoaventura.R
import com.example.juegonarrativoaventura.ui.endings.EndingActivity
import com.example.juegonarrativoaventura.ui.world.WorldActivity
import com.example.juegonarrativoaventura.utils.ThemeManager

class ForestRegionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeManager.applyTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_region_forest)

        findViewById<Button>(R.id.btn_ending1).setOnClickListener {
            navigateToEnding(1)
        }
        findViewById<Button>(R.id.btn_ending2).setOnClickListener {
            navigateToEnding(2)
        }

        findViewById<Button>(R.id.btn_restart).setOnClickListener {
            restartAdventure()
        }
    }

    private fun navigateToEnding(endingId: Int) {
        val intent = Intent(this, EndingActivity::class.java).apply {
            putExtra("ENDING_ID", endingId)
        }
        startActivity(intent)
        overridePendingTransition(R.anim.zoom_in, R.anim.fade_out)
    }

    private fun restartAdventure() {
        val intent = Intent(this, WorldActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        finish()
    }
}