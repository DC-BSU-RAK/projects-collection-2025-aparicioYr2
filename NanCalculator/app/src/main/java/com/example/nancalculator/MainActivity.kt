package com.example.nancalculator

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val artistSpinner: Spinner = findViewById(R.id.artistSpinner)
        val genreSpinner: Spinner = findViewById(R.id.genreSpinner)

        val resultText: TextView = findViewById(R.id.resultText)
        val enterButton: Button = findViewById(R.id.enterButton)


        val artists = arrayOf("Lady Gaga", "Tate McRae", "Ariana Grande", "Doja Cat", "The Weeknd", "Tame Impala", "Sabrina Carpenter", "Laufey")
        val genres = arrayOf("Pop", "Rock", "Alternative", "R&B/Soul", "Hip-Hop", "Electronic/EDM", "Jazz", "Classical")


        artistSpinner.adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item, artists)
        genreSpinner.adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item, genres)


        val artistAndGenre = mapOf(
            "Lady Gaga" to mapOf(
                "Pop" to "'Disease' by Lady Gaga",
                "Rock" to "'Perfect Celebrity' by Lady Gaga",
                "Alternative" to "'Killah' by Lady Gaga",
                "R&B/Soul" to "'Dope' by Lady Gaga",
                "Hip-Hop" to "'Jewels N’ Drugs' by Lady Gaga (feat. T.I., Too Short, Twista)",
                "Electrionic/EDM" to "'Stupid Love' by Lady Gaga",
                "Jazz" to "'Anything Goes' by Lady Gaga",
                "Classical" to "'Speechless' by Lady Gaga"
            ),
            "Tate McRae" to mapOf(
                "Pop" to "'Sports Car' by Tate McRae",
                "Rock" to "'She's All I wanna be' by Tate McRae",
                "Alternative" to "'Chaotic' by Tate McRae",
                "R&B/Soul" to "'You Broke Me First' by Tate McRae",
                "Hip-Hop" to "'Uh oh' by Tate McRae",
                "Electrionic/EDM" to "'10:35' by Tate McRae (feat. Tiësto)",
            ),
            "Ariana Grande" to mapOf(
                "Pop" to "'The Boy is Mine' by Ariana Grande",
                "Rock" to "'Dangerous Woman' by Ariana Grande",
                "Alternative" to "'Imperfect for You' by Ariana Grande",
                "R&B/Soul" to "'My Hair' by Ariana Grande",
                "Hip-Hop" to "'7 Rings' by Ariana Grande",
                "Electrionic/EDM" to "'Into You' by Ariana Grande"
            ),
            "Doja Cat" to mapOf(
                "Pop" to "'Say So' by Doja Cat",
                "Rock" to "'Attention' by Doja Cat",
                "Alternative" to "'Paint the Town Red' by Doja Cat",
                "R&B/Soul" to "'Streets' by Doja Cat",
                "Hip-Hop" to "'Tia Tamera' by Doja Cat (feat. Rico Nasty)",
                "Electrionic/EDM" to "'You Right' by Doja Cat (feat. The Weeknd)"
            ),
            "The Weeknd" to mapOf(
                "Pop" to "'Blinding Lights' by The Weeknd",
                "Rock" to "'Sacrifice' by The Weeknd",
                "Alternative" to "'In The Night' by The Weeknd",
                "R&B/Soul" to "'Earned It' by The Weeknd",
                "Hip-Hop" to "'Reminder' by The Weeknd",
                "Electrionic/EDM" to "'How Do I Make You Love Me?' by The Weeknd"
            ),
            "Tame Impala" to mapOf(
                "Pop" to "'Borderline' by Tame Impala",
                "Rock" to "'Lucidity' by Tame Impala",
                "Alternative" to "'New Person, Same Old Mistakes' by Tame Impala",
                "R&B/Soul" to "'Love/Paranoia' by Tame Impala",
                "Electrionic/EDM" to "'Let It Happen' by Tame Impala",
                "Jazz" to "'Let It Happen' by Tame Impala",
                "Classical" to "'Let You Break My Heart Again' by Laufey"
            ),
            "Sabrina Carpenter" to mapOf(
                "Pop" to "'Espresso' by Sabrina Carpenter",
                "Rock" to "'Can't Blame a Girl for Trying' by Sabrina Carpenter",
                "Alternative" to "'Looking at Me' by Sabrina Carpenter",
                "R&B/Soul" to "'Why' by Sabrina Carpenter",
                "Hip-Hop" to "'Exhale' by Sabrina Carpenter",
                "Electrionic/EDM" to "'Eyes Wide Open' by Sabrina Carpenter",
                "Jazz" to "'Spaces' by Sabrina Carpenter"
            ),
            "Laufey" to mapOf(
                "Pop" to "'From The Start' by Laufey",
                "Jazz" to "'Valentine' by Laufey",
                "Classical" to "'Let You Break My Heart Again' by Laufey"
            )
        )

        enterButton.setOnClickListener {
            val selectedArtist = artistSpinner.selectedItem.toString() //Get value selected from emirates spinner
            val selectedGenre = genreSpinner.selectedItem.toString() //Get value selected from category spinner

            val songResult = artistAndGenre[selectedArtist]?.get(selectedGenre) ?: "No matching song"

            resultText.text = songResult


        }

        val instructionsButton: Button = findViewById(R.id.instructionsButton)
        instructionsButton.setOnClickListener {
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.activity_popup, null)

            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT

            val instructionsWindow = PopupWindow(popupView, width, height, true)
            instructionsWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // Allow transparency
            instructionsWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0)

            val closeButton: Button = popupView.findViewById(R.id.closeButton)
            closeButton.setOnClickListener {
                instructionsWindow.dismiss()
            }
        }
    }
}