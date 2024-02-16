package com.example.blindbattle

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class BotIntro: AppCompatActivity() , TextToSpeech.OnInitListener{
    private lateinit var tts: TextToSpeech
    private lateinit var button: Button
    private lateinit var playername:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_bot)

        // Initialize TextToSpeech in the onCreate method
        tts = TextToSpeech(this, this)


        val intent = intent
        val receivedMessage = intent.getIntExtra("value", 0)
        val player = intent.getStringExtra("Player")
        if (player != null) {
            playername=player
        }

        button = findViewById(R.id.button)
        button.setOnClickListener {
            if (receivedMessage == 1) {
                val easy = Intent(this, Easy_Blind_Mode::class.java)
                easy.putExtra("Player", player)
                startActivity(easy)
                finish()
            } else if (receivedMessage == 2) {
                val medium = Intent(this, Medium_Blind_Mode::class.java)
                medium.putExtra("Player", player)
                startActivity(medium)
                finish()
            } else if (receivedMessage == 3) {
                val hard = Intent(this, Hard_Blind_Mode::class.java)
                hard.putExtra("Player", player)
                startActivity(hard)
                finish()
            }
        }


    }

    private fun speak(message: String, speechRate: Float = 0.78f) {
        if (tts.isSpeaking) {
            tts.stop()
        }
        tts.setSpeechRate(speechRate)
        tts.speak(message, TextToSpeech.QUEUE_ADD, null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // Set language and other configurations if needed
            tts.language = Locale.US
            Toast.makeText(this, "TextToSpeech initialization success", Toast.LENGTH_SHORT).show()
            // You can speak a default message here if
            speak("Hello ${playername} \n Let us talk about rules and instructions /n If you already know rules Tap Once for commence the game \n or \n Let us start the introduction \n It is a 3 cross 3 grid with cells labeled \n first row: a1,a2,a3 \n second row: b1,b2,b3 \n third row: c1,c2,c3 \n Bot uses CROSS and Player uses NOUGHT \n When it's your turn\n Explore the cells in the grid by using single tab on cell to know about the cell status\n and tap two times on an empty cell to place your symbol \n After each move, the game announces whenever your turn is. For instance, $playername's Turn \n The game declares a winner if a player forms a line horizontally, vertically, or diagonally\n congratulatory message will be announced to the player wins \n If all cells are filled and no winner is declared, the game recognizes a draw\nWhenever the game ends\n You have two options\n Continue or go to home screen\n  Please tab once for commence the game")
        } else {
            Toast.makeText(this, "TextToSpeech initialization failed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        // Release TextToSpeech resources in onDestroy
        if (tts.isSpeaking) {
            tts.stop()
        }
        tts.shutdown()
        super.onDestroy()
    }
}
