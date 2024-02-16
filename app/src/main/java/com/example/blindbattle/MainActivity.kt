package com.example.blindbattle

import  android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var button: Button
    private var i: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize TextToSpeech in the onCreate method
        tts = TextToSpeech(this, this)

        button = findViewById(R.id.button)

        button.setOnClickListener {
            i++
            val handler = Handler()

            handler.postDelayed({
                if (i == 1) {
                    speak("New Game Selected")
                    startActivity(Intent(this, PlayerNamenew::class.java))
                } else if (i == 2) {
                    speak("Play with Bot Selected")
                    startActivity(Intent(this, PlayWithBotOptionSelection::class.java))
                }else if(i==3){
                    speak("You Choose Exit Option")
                    val handler=Handler()
                    handler.postDelayed({
                        moveTaskToBack(true)
                        android.os.Process.killProcess(android.os.Process.myPid())
                        System.exit(1)
                    },2000)

                }
                i = 0
            }, 550)
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
            tts.language = Locale.US
            Toast.makeText(this, "TextToSpeech initialization success", Toast.LENGTH_SHORT).show()
            speak("Welcome to Home Menu \n You have three options \n Tap once for a New Game \n Tap twice for Play with Bot\n Tap thrice for Exit")
        } else {
            Toast.makeText(this, "TextToSpeech initialization failed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        if (tts.isSpeaking) {
            tts.stop()
        }
        tts.shutdown()
        super.onDestroy()
    }
}
