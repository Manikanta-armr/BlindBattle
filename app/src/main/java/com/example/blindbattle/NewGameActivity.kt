package com.example.blindbattle

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class NewGameActivity : AppCompatActivity() , TextToSpeech.OnInitListener {

    enum class Turn {
        NOUGHT,
        CROSS
    }

    private var currentturn = Turn.CROSS
    private lateinit var tts: TextToSpeech
    private lateinit var a1: Button
    private lateinit var a2: Button
    private lateinit var a3: Button
    private lateinit var b1: Button
    private lateinit var b2: Button
    private lateinit var b3: Button
    private lateinit var c1: Button
    private lateinit var c2: Button
    private lateinit var c3: Button
    private lateinit var top: Button
    private lateinit var bottom: Button
    private lateinit var operate:Button
    private lateinit var operate1:Button
    private var player1: String? = null
    private var player2: String? = null
    private var player1Score:Int=0
    private var player2Score:Int=0
    private var a: Int = 0
    private var b: Int = 0
    private var c: Int = 0
    private var d: Int = 0
    private var e: Int = 0
    private var f: Int = 0
    private var g: Int = 0
    private var h: Int = 0
    private var i: Int = 0
    private var gameInProgress: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)

        val intent = intent
        player1 = intent.getStringExtra("Player1")
        player2 = intent.getStringExtra("Player2")


        tts = TextToSpeech(this, this)

        top=findViewById(R.id.top)

        top.setOnClickListener{
            speak("The Board Is in Center")
        }

        bottom=findViewById(R.id.bottom)

        bottom.setOnClickListener {
            speak("The Board Is in Center")
        }

        a1 = findViewById(R.id.a1)

        a1.setOnClickListener {
            if(gameInProgress) {
                a++
                val handler = Handler()

                handler.postDelayed({
                    if (a == 1) {
                        checkVal("a1", it)
                    } else if (a == 2) {
                        place("a1", it)
                    }
                    a = 0
                }, 500)
            }
        }

        a2 = findViewById(R.id.a2)

        a2.setOnClickListener {
            if(gameInProgress) {
                b++
                val handler = Handler()

                handler.postDelayed({
                    if (b == 1) {
                        speak("a2")
                        checkVal("a2", it)

                    } else if (b == 2) {
                        place("a2", it)
                    }
                    b = 0
                }, 500)
            }
        }

        a3 = findViewById(R.id.a3)

        a3.setOnClickListener {
            if(gameInProgress) {
                c++
                val handler = Handler()

                handler.postDelayed({
                    if (c == 1) {
                        speak("a3")
                        checkVal("a3", it)

                    } else if (c == 2) {
                        place("a3", it)
                    }
                    c = 0
                }, 500)
            }
        }

        b1 = findViewById(R.id.b1)

        b1.setOnClickListener {
            if(gameInProgress) {
                d++
                val handler = Handler()

                handler.postDelayed({
                    if (d == 1) {
                        speak("b1")
                        checkVal("b1", it)

                    } else if (d == 2) {
                        place("b1", it)
                    }
                    d = 0
                }, 500)
            }
        }

        b2 = findViewById(R.id.b2)

        b2.setOnClickListener {
            if(gameInProgress) {
                e++
                val handler = Handler()

                handler.postDelayed({
                    if (e == 1) {
                        speak("b2")
                        checkVal("b2", it)

                    } else if (e == 2) {
                        place("b2", it)
                    }
                    e = 0
                }, 500)
            }
        }

        b3 = findViewById(R.id.b3)

        b3.setOnClickListener {
            if(gameInProgress) {
                f++
                val handler = Handler()

                handler.postDelayed({
                    if (f == 1) {
                        speak("b3")
                        checkVal("b3", it)

                    } else if (f == 2) {
                        place("b3", it)
                    }
                    f = 0
                }, 500)
            }
        }

        c1 = findViewById(R.id.c1)

        c1.setOnClickListener {
            if(gameInProgress) {
                g++
                val handler = Handler()

                handler.postDelayed({
                    if (g == 1) {
                        speak("c1")
                        checkVal("c1", it)

                    } else if (g == 2) {
                        place("c1", it)
                    }
                    g = 0
                }, 500)
            }
        }

        c2 = findViewById(R.id.c2)

        c2.setOnClickListener {
            if(gameInProgress) {
                h++
                val handler = Handler()

                handler.postDelayed({
                    if (h == 1) {
                        speak("c2")
                        checkVal("c2", it)

                    } else if (h == 2) {
                        place("c2", it)
                    }
                    h = 0
                }, 500)
            }
        }

        c3 = findViewById(R.id.c3)

        c3.setOnClickListener {
            if(gameInProgress) {
                i++
                val handler = Handler()

                handler.postDelayed({
                    if (i == 1) {
                        speak("c3")
                        checkVal("c3", it)

                    } else if (i == 2) {
                        place("c3", it)
                    }
                    i = 0
                }, 500)
            }
        }

        operate=findViewById(R.id.choose)

        operate.setOnClickListener {
            i++
            val handler = Handler()

            handler.postDelayed({
                if (i == 1) {
                    reset()
                } else if (i == 2) {
                    speak("You selected Home menu Option")
                    val handler=Handler()
                    handler.postDelayed({
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    },2500)
                }
                i = 0
            }, 500)
        }

        operate1=findViewById(R.id.operate1)

        operate1.setOnClickListener {
            i++
            val handler = Handler()

            handler.postDelayed({
                if (i == 1) {
                    reset()
                } else if (i == 2) {
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }
                i = 0
            }, 500)
        }


    }

    private fun place(s: String, it: View?) {
        val text = (it as Button).text
        if (text != "") {
            checkVal("$s is already", it)
        } else {
            addToBoard(s, it)
            if (checkdraw()) {
                val handler = Handler()
                speak("Oh it's a Draw")
                handler.postDelayed({
                    gotoScoreActivity()
                }, 3000)
            }
        }
    }

    private fun gotoScoreActivity() {
        speak("You have two options\n At the bottom of the screen \n Please tap Once for continue \n tap twice for Home menu")
        bottom.visibility=View.GONE
        operate.visibility = View.VISIBLE
        top.visibility=View.GONE
        operate1.visibility = View.VISIBLE
    }

    private fun reset() {
        a1.text=""
        a2.text=""
        a3.text=""
        b1.text=""
        b2.text=""
        b3.text=""
        c1.text=""
        c2.text=""
        c3.text=""
        player1 = player2.also { player2 = player1 }
        val temp = player1Score
        player1Score=player2Score
        player2Score=temp
        bottom.visibility=View.VISIBLE
        operate.visibility = View.GONE
        top.visibility=View.VISIBLE
        operate1.visibility = View.GONE
        currentturn=Turn.CROSS
        gameInProgress=true
        speak("You selected Continue \n \n $player1 score $player1Score \n $player2 score $player2Score \n $player1's Turn")
    }

    private fun checkdraw(): Boolean {
        if (a1.text != "" && a2.text != "" && a3.text != "" && b1.text != "" && b2.text != "" && b3.text != "" && c1.text != "" && c2.text != "" && c3.text != "") {
            return true
        }
        return false
    }

    private fun checkmatch(button: Button, s: String): Boolean = button.text == s


    private fun checkforWinning(s: String): Boolean {

        //Row wise
        if (checkmatch(a1, s) && checkmatch(a2, s) && checkmatch(a3, s))
            return true
        if (checkmatch(b1, s) && checkmatch(b2, s) && checkmatch(b3, s))
            return true
        if (checkmatch(c1, s) && checkmatch(c2, s) && checkmatch(c3, s))
            return true

        //Column wise
        if (checkmatch(a1, s) && checkmatch(b1, s) && checkmatch(c1, s))
            return true
        if (checkmatch(a2, s) && checkmatch(b2, s) && checkmatch(c2, s))
            return true
        if (checkmatch(a3, s) && checkmatch(b3, s) && checkmatch(c3, s))
            return true

        //Diagonal wise
        if (checkmatch(a1, s) && checkmatch(b2, s) && checkmatch(c3, s))
            return true
        if (checkmatch(a3, s) && checkmatch(b2, s) && checkmatch(c1, s))
            return true
        return false
    }

    private fun addToBoard(s: String, it: Button) {
        val button = (it as Button)
        if (currentturn == Turn.CROSS) {
            button.text = CROSS
            button.setTextColor(resources.getColor(R.color.cross_color))
            currentturn = Turn.NOUGHT
            speak("$player1 selected $s\n\n $player2's Turn")
            if (checkforWinning(CROSS)) {
                val handler = Handler()
                gameInProgress=false
                speak("Congratulations $player1 you are the winner")
                player1Score+=1
                handler.postDelayed({
                    gotoScoreActivity()
                }, 3000)
            }
        } else if (currentturn == Turn.NOUGHT) {
            button.text = NOUGHT
            button.setTextColor(resources.getColor(R.color.nought_color))
            currentturn = Turn.CROSS
            speak("$player2 selected $s\n\n $player1's Turn")
            if (checkforWinning(NOUGHT)) {
                val handler = Handler()
                gameInProgress=false
                speak("Congratulations $player2 you are the winner")
                player2Score+=1
                handler.postDelayed({
                    gotoScoreActivity()
                }, 4000)
            }
        }
    }

    private fun checkVal(s: String, it: View?) {
        when ((it as Button).text) {
            "" -> {
                speak("$s is Empty")
            }

            CROSS -> {
                speak("$s is CROSS")
            }

            NOUGHT -> {
                speak("$s is NOUGHT")
            }
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
            speak("$player1's Turn")
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

    companion object {
        const val NOUGHT = "O"
        const val CROSS = "X"
    }
}