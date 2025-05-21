package com.example.expresso

import android.content.Context

object SharedPrefHandler {
    private const val PREF_NAME = "AppPrefs"
    private const val MUSIC_KEY = "music_on"

    fun isMusicOn(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(MUSIC_KEY, true)
    }

    // Renamed method for clarity and usage convenience
    fun setMusicOn(context: Context, isOn: Boolean) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(MUSIC_KEY, isOn).apply()
    }
}
