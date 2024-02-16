package com.example.blindbattle

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import kotlin.properties.Delegates

class PlayWithBotOptionSelection : AppCompatActivity(), TextToSpeech.OnInitListener{
    private lateinit var tts: TextToSpeech
    private lateinit var button: Button
    private var i: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bot_options)

        // Initialize TextToSpeech in the onCreate method
        tts = TextToSpeech(this, this)

        button = findViewById(R.id.button)

        button.setOnClickListener {
            i++
            val handler = Handler()

            handler.postDelayed({
                if (i == 1) {
                    speak("Easy Level Selected")
                    val intent = Intent(this, PlayNamebot::class.java)
                    intent.putExtra("value",1)
                    startActivity(intent)
                    finish()
                } else if (i == 2) {
                    speak("Medium Level Selected")
                    val intent = Intent(this, PlayNamebot::class.java)
                    intent.putExtra("value",2)
                    startActivity(intent)
                    finish()
                } else if(i==3){
                    speak("Hard Level Selected")
                    val intent = Intent(this, PlayNamebot::class.java)
                    intent.putExtra("value",3)
                    startActivity(intent)
                    finish()
                }
                i = 0
            }, 700)
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
            speak("You have three options \n Tap once for Easy Level \n Tap twice for Medium Level \n Tap Thrice for Hard Level")
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
