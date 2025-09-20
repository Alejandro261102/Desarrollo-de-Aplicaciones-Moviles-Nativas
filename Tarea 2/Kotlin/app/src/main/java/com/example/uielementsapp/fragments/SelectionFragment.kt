package com.example.uielementsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.uielementsapp.R

class SelectionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_selection, container, false)
        val cb1 = v.findViewById<android.widget.CheckBox>(R.id.cb1)
        val cb2 = v.findViewById<android.widget.CheckBox>(R.id.cb2)
        val rg  = v.findViewById<android.widget.RadioGroup>(R.id.rg)
        val sw  = v.findViewById<android.widget.Switch>(R.id.sw)
        val tv  = v.findViewById<android.widget.TextView>(R.id.tv_estado)

        fun render() {
            val cbs = listOfNotNull(if (cb1.isChecked) "A" else null, if (cb2.isChecked) "B" else null).joinToString(", ")
            val rb  = when (rg.checkedRadioButtonId) { R.id.rb_si -> "Sí"; R.id.rb_no -> "No"; else -> "—" }
            val swt = if (sw.isChecked) "ON" else "OFF"
            tv.text = "Estado: CheckBox=[$cbs] | Radio=$rb | Switch=$swt"
        }

        cb1.setOnCheckedChangeListener { _, _ -> render() }
        cb2.setOnCheckedChangeListener { _, _ -> render() }
        rg.setOnCheckedChangeListener { _, _ -> render() }
        sw.setOnCheckedChangeListener { _, _ -> render() }

        render()
        return v
    }
}