package com.example.uielementsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.uielementsapp.fragments.TextFieldsFragment
import com.example.uielementsapp.fragments.ButtonsFragment
import com.example.uielementsapp.fragments.SelectionFragment
import com.example.uielementsapp.fragments.ListFragment
import com.example.uielementsapp.fragments.InfoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: com.google.android.material.bottomnavigation.BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_navigation)
        replaceFragment(com.example.uielementsapp.fragments.TextFieldsFragment())

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_textfields -> replaceFragment(com.example.uielementsapp.fragments.TextFieldsFragment())
                R.id.nav_buttons    -> replaceFragment(com.example.uielementsapp.fragments.ButtonsFragment())
                R.id.nav_selection  -> replaceFragment(com.example.uielementsapp.fragments.SelectionFragment())
                R.id.nav_list       -> replaceFragment(com.example.uielementsapp.fragments.ListFragment())
                R.id.nav_info       -> replaceFragment(com.example.uielementsapp.fragments.InfoFragment())
            }
            true
        }
    }

    fun selectTab(itemId: Int) {
        bottomNav.selectedItemId = itemId
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
