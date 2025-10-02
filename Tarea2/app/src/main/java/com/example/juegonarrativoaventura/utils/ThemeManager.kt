package com.example.juegonarrativoaventura.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.juegonarrativoaventura.R

object ThemeManager {
    private const val PREFERENCES_FILE = "theme_prefs"
    private const val IS_DARK_MODE_KEY = "is_dark_mode"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
    }

    fun isDarkMode(context: Context): Boolean {
        return getPreferences(context).getBoolean(IS_DARK_MODE_KEY, false) // El claro es el default
    }

    fun saveThemePreference(context: Context, isDarkMode: Boolean) {
        getPreferences(context).edit().putBoolean(IS_DARK_MODE_KEY, isDarkMode).apply()
    }

    fun applyTheme(activity: AppCompatActivity) {
        if (isDarkMode(activity)) {
            activity.setTheme(R.style.Theme_App_Dark)
        } else {
            activity.setTheme(R.style.Theme_App_Light)
        }
    }
}