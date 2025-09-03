package com.example.uielementsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.uielementsapp.R

class ListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {val v = inflater.inflate(R.layout.fragment_list, container, false)
        val listView = v.findViewById<android.widget.ListView>(R.id.listView)
        val items = listOf("Manzana", "Banana", "Naranja", "Fresa", "Mango")
        val adapter = android.widget.ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, pos, _ ->
            android.widget.Toast.makeText(requireContext(), "Seleccionaste: ${items[pos]}", android.widget.Toast.LENGTH_SHORT).show()
            // Ejemplo: saltar a Info
            (requireActivity() as com.example.uielementsapp.MainActivity).selectTab(R.id.nav_info)
        }
        return v
    }
}
