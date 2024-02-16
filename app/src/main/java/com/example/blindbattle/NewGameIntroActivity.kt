package com.example.blindbattle

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class NewGameIntroActivity: AppCompatActivity() , TextToSpeech.OnInitListener{
    private lateinit var tts: TextToSpeech
    private lateinit var button: Button
    private var i: Int = 0
    private lateinit var player1name:String
    private lateinit var player2name:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_newgame)

        val intent = intent
        val player1 = intent.getStringExtra("Player1")
        val player2 = intent.getStringExtra("Player2")

        if (player1 != null) {
            player1name=player1
        }
        if (player2 != null) {
            player2name=player2
        }
        // Optional: You can pass these values to NewGameActivity
        val newGameIntent = Intent(this, NewGameActivity::class.java)
        newGameIntent.putExtra("Player1", player1)
        newGameIntent.putExtra("Player2", player2)


        // Initialize TextToSpeech in the onCreate method
        tts = TextToSpeech(this, this)

        button = findViewById(R.id.button)

        button.setOnClickListener {
            startActivity(newGameIntent)
            finish()
        }
    }


    private fun speak(message: String, speechRate: Float = 0.78f) {
        if (tts.isSpeaking) {
            tts.stop()
        }
        // Set the speech rate
        tts.setSpeechRate(speechRate)
        // Speak the message
        tts.speak(message, TextToSpeech.QUEUE_ADD, null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // Set language and other configurations if needed
            tts.language = Locale.US
            Toast.makeText(this, "TextToSpeech initialization success", Toast.LENGTH_SHORT).show()
            // You can speak a default message here if required
            speak("Hello $player1name and $player2name \n Let us talk about rules and instructions /n If you already know rules Tap Once for commence the game \n or \n Let us start the introduction \n It is a 3 cross 3 grid with cells labeled \n first row: a1,a2,a3 \n second row: b1,b2,b3 \n third row: c1,c2,c3 \n Player 1 uses CROSS and Player 2 uses NOUGHT \n When it's your turn\n Explore the cells in the grid by using single tab on cell to know about the cell status\n and tap two times on an empty cell to place your symbol \n After each move, the game announces whose turn it is. For instance, $player1name's Turn or $player2name's Turn \n The game declares a winner if a player forms a line horizontally, vertically, or diagonally\n congratulatory message will be announced to the player whoever wins \n If all cells are filled and no winner is declared, the game recognizes a draw\nWhenever the game ends \nYou have two options\n continue or exit to home screen\nthis are the game rules\n  Please tab once for commence the game")
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
