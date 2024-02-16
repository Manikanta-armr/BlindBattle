package com.example.blindbattle

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.view.MotionEvent
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.Locale

class PlayNamebot: AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var recognizerIntent: Intent
    private var received:Int=1

    private var player: String? = null

    private val RECORD_AUDIO_PERMISSION_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playernames)

        val intent = intent
        received=intent.getIntExtra("value",1)

        checkPermission()

        tts = TextToSpeech(this, this)
        val recordButton=findViewById<Button>(R.id.names)
        recordButton.setOnLongClickListener {

            if (tts.isSpeaking) {
                tts.stop()
            }
            startVoiceRecognition()
            true
        }

        recordButton.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                // button is released, stop voice recognition
                speechRecognizer.stopListening()
            }
            false
        }

        initializeSpeechRecognizer()
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                RECORD_AUDIO_PERMISSION_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RECORD_AUDIO_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, you can proceed with speech recognition
            } else {
                Toast.makeText(
                    this,
                    "Permission denied. Speech recognition won't work.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun initializeSpeechRecognizer() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        recognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )

        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {}

            override fun onBeginningOfSpeech() {}

            override fun onRmsChanged(rmsdB: Float) {}

            override fun onBufferReceived(buffer: ByteArray?) {}

            override fun onEndOfSpeech() {}

            override fun onError(error: Int) {
                val errorMessage: String = when (error) {
                    SpeechRecognizer.ERROR_AUDIO -> "ERROR_AUDIO"
                    SpeechRecognizer.ERROR_CLIENT -> "ERROR_CLIENT"
                    SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "ERROR_INSUFFICIENT_PERMISSIONS"
                    SpeechRecognizer.ERROR_NETWORK -> "ERROR_NETWORK"
                    SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "ERROR_NETWORK_TIMEOUT"
                    SpeechRecognizer.ERROR_NO_MATCH -> "ERROR_NO_MATCH"
                    SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "ERROR_RECOGNIZER_BUSY"
                    SpeechRecognizer.ERROR_SERVER -> "ERROR_SERVER"
                    SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "ERROR_SPEECH_TIMEOUT"
                    else -> "Unknown error: $error"
                }
                Toast.makeText(this@PlayNamebot, "Error in speech recognition: $errorMessage", Toast.LENGTH_SHORT).show()

            }


            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                val playerName = matches?.get(0) ?: ""
                player= playerName
                Toast.makeText(this@PlayNamebot, "Player : $playerName", Toast.LENGTH_SHORT).show()
                gotoactive()
            }

            override fun onPartialResults(partialResults: Bundle?) {}

            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }

    private fun gotoactive() {
        val botintent = Intent(this, BotIntro::class.java)
        botintent.putExtra("Player", player)
        botintent.putExtra("value",received)
        startActivity(botintent)
        finish()
    }


    private fun startVoiceRecognition() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (SpeechRecognizer.isRecognitionAvailable(this)) {
                speechRecognizer.startListening(recognizerIntent)
            } else {
                Toast.makeText(
                    this,
                    "Speech recognition service is not available",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                this,
                "Record audio permission is not granted. Speech recognition won't work.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onStop() {
        super.onStop()
        speechRecognizer.destroy()
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
            speak("Welcome To Tic Tac Toe Game \n Please tell your name by long press on the screen and remove your finger whenever you completed saying your name \n The name should contain letters between 1 to 6 words and you should tell your name after 2 seconds when you long press on the screen \n Player ,please say your name")
            val handler= Handler()
            handler.postDelayed({
                Toast.makeText(this, "Player please say your name:", Toast.LENGTH_SHORT).show()
            },18000)

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
