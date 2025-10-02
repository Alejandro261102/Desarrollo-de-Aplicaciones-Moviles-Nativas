package com.example.juegonarrativoaventura.ui.endings

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.juegonarrativoaventura.R
import com.example.juegonarrativoaventura.ui.world.WorldActivity
import com.example.juegonarrativoaventura.utils.ThemeManager

class EndingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeManager.applyTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ending)

        val endingId = intent.getIntExtra("ENDING_ID", 0)

        val title = findViewById<TextView>(R.id.tv_ending_title)
        val description = findViewById<TextView>(R.id.tv_ending_description)

        when (endingId) {
            1 -> {
                title.text = "Final 1: La Caída del Valiente"
                description.text = "El puente no soportó tu peso y te precipitaste al abismo. Tu valentía fue grande, pero la madera era antigua. La leyenda de tu audacia se contará en susurros."
            }
            2 -> {
                title.text = "Final 2: El Pacto del Río"
                description.text = "El barquero te llevó a una orilla secreta, revelándote un tesoro olvidado por el tiempo. Tu sabiduría para elegir aliados te ha hecho rico y poderoso."
            }
            3 -> {
                title.text = "Final 3: El Corazón de la Montaña"
                description.text = "Dentro de la cueva, encontraste un dragón de cristal dormido. Al no molestarlo, te concedió un fragmento de su poder. Ahora controlas la magia de la tierra."
            }
            4 -> {
                title.text = "Final 4: La Vista del Conquistador"
                description.text = "Tras una ardua escalada, llegaste a la cima más alta del reino. Desde allí, pudiste ver el mundo entero. Tu perseverancia te ha convertido en una leyenda viviente."
            }
            else -> {
                title.text = "Final Desconocido"
                description.text = "El camino que tomaste no llevó a ninguna parte conocida."
            }
        }

        findViewById<Button>(R.id.btn_restart).setOnClickListener {
            restartAdventure()
        }
    }

    private fun restartAdventure() {
        val intent = Intent(this, WorldActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        finish()
    }
}