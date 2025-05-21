package com.example.expresso

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupWindow
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dunkinButton: ImageButton = findViewById(R.id.dunkinButton)
        dunkinButton.setOnClickListener {
            val intent = Intent(this, MainActivityDunkin::class.java)
            startActivity(intent)
        }

        val mcdonaldsButton: ImageButton = findViewById(R.id.mcdonaldsButton)
        mcdonaldsButton.setOnClickListener {
            val intent = Intent(this, MainActivityMcdonalds::class.java)
            startActivity(intent)
        }

        val jollibeeButton: ImageButton = findViewById(R.id.jollibeeButton)
        jollibeeButton.setOnClickListener {
            val intent = Intent(this, MainActivityJollibee::class.java)
            startActivity(intent)
        }

        val settingsButton: ImageButton = findViewById(R.id.settingsButton)
        settingsButton.setOnClickListener {
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.activity_popup, null)

            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT

            val settingsWindow = PopupWindow(popupView, width, height, true)
            settingsWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // Allow transparency
            settingsWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0)

            val closeButton: Button = popupView.findViewById(R.id.closeButton)
            closeButton.setOnClickListener {
                settingsWindow.dismiss()
            }


            // Play and Pause button setup
            val playButton: ImageButton = popupView.findViewById(R.id.playButton)
            val stopButton: ImageButton = popupView.findViewById(R.id.stopButton)

            playButton.setOnClickListener {
                startService(Intent(this, MusicService::class.java))
                SharedPrefHandler.setMusicOn(this, true)
            }

            stopButton.setOnClickListener {
                stopService(Intent(this, MusicService::class.java))
                SharedPrefHandler.setMusicOn(this, false)
            }
        }

        // Start music service on app launch if music was ON before
        if (SharedPrefHandler.isMusicOn(this)) {
            startService(Intent(this, MusicService::class.java))
        }
    }
}
