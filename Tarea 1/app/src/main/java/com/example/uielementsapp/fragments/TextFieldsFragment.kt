package com.example.uielementsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.uielementsapp.R

class TextFieldsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_textfields, container, false)
        val input = v.findViewById<android.widget.EditText>(R.id.editText)
        val out   = v.findViewById<android.widget.TextView>(R.id.textResult)
        val btn   = v.findViewById<android.widget.Button>(R.id.btnShow)
        btn.setOnClickListener {
            out.text = "¡Hola, ${input.text}!"
            input.text.clear()
        }
        return v
    }
}
