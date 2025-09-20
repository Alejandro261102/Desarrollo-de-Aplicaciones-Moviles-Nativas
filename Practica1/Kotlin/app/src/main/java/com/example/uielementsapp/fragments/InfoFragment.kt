package com.example.uielementsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.uielementsapp.R

class InfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_info, container, false)
        val progress = v.findViewById<android.widget.ProgressBar>(R.id.progress)
        val btn = v.findViewById<android.widget.Button>(R.id.btn_progress)

        btn.setOnClickListener {
            progress.progress = 0
            // Simulación simple en UI Thread con postDelayed
            val handler = android.os.Handler(android.os.Looper.getMainLooper())
            var p = 0
            val r = object : Runnable {
                override fun run() {
                    if (p <= 100) {
                        progress.progress = p
                        p += 10
                        handler.postDelayed(this, 150)
                    }
                }
            }
            handler.post(r)
        }
        return v
    }
}
