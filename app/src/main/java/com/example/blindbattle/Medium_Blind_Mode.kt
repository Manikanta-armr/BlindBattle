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

class Medium_Blind_Mode : AppCompatActivity(), TextToSpeech.OnInitListener {

    enum class Turn{
        NOUGHT,
        CROSS
    }

    var checklist = mutableMapOf<Button, String>()
    private var currentturn=Turn.CROSS
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
    private var player: String? = null
    private var playerScore:Int=0
    private var boturn:Int=0
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
    var putlist = mutableMapOf<Button, String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hard_blind)

        val intent = intent
        player = intent.getStringExtra("Player")

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

        checklist[a1] = present(a1)
        checklist[a2] = present(a2)
        checklist[a3] = present(a3)
        checklist[b1] = present(b1)
        checklist[b2] = present(b2)
        checklist[b3] = present(b3)
        checklist[c1] = present(c1)
        checklist[c2] = present(c2)
        checklist[c3] = present(c3)

        putlist[a1]="a1"
        putlist[a2]="a2"
        putlist[a3]="a3"
        putlist[b1]="b1"
        putlist[b2]="b2"
        putlist[b3]="b3"
        putlist[c1]="c1"
        putlist[c2]="c2"
        putlist[c3]="c3"

        val handler= Handler()

        handler.postDelayed({
            botMakeMove()
            currentturn=Turn.NOUGHT
        },1500)
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
        checklist[a1] = present(a1)
        checklist[a2] = present(a2)
        checklist[a3] = present(a3)
        checklist[b1] = present(b1)
        checklist[b2] = present(b2)
        checklist[b3] = present(b3)
        checklist[c1] = present(c1)
        checklist[c2] = present(c2)
        checklist[c3] = present(c3)
        bottom.visibility=View.VISIBLE
        operate.visibility = View.GONE
        currentturn=Turn.CROSS
        gameInProgress=true
        boturn=0
        speak("You selected Continue \n \n and your score is $playerScore \n Bot will make a move")
        val handler= Handler()

        handler.postDelayed({
            botMakeMove()
            currentturn=Turn.NOUGHT
        },2500)
    }

    private fun place(s: String, it: View?) {
        val text=(it as Button).text
        if(text!=""){
            checkVal("$s is already",it)
        }
        else{
            addToBoard(s,it)
        }
    }

    private fun gotomain() {
        speak("You have two options\n At the bottom of the screen \n Please tap Once for continue \n tap twice for Home menu")
        bottom.visibility=View.GONE
        operate.visibility = View.VISIBLE
    }

    private fun checkdraw(): Boolean {
        if(a1.text!="" && a2.text!="" && a3.text!="" && b1.text!="" && b2.text!="" && b3.text!="" && c1.text!="" && c2.text!="" && c3.text!=""){
            return true
        }
        return false
    }


    private fun checkmatch(button: Button, s: String): Boolean = button.text ==  s


    private fun checkforWinning(s: String): Boolean {

        //Row wise
        //Row wise
        if(checkmatch(a1,s) && checkmatch(a2,s) && checkmatch(a3,s))
            return true
        if(checkmatch(b1,s) && checkmatch(b2,s) && checkmatch(b3,s))
            return true
        if(checkmatch(c1,s) && checkmatch(c2,s) && checkmatch(c3,s))
            return true

        //Column wise
        if(checkmatch(a1,s) && checkmatch(b1,s) && checkmatch(c1,s))
            return true
        if(checkmatch(a2,s) && checkmatch(b2,s) && checkmatch(c2,s))
            return true
        if(checkmatch(a3,s) && checkmatch(b3,s) && checkmatch(c3,s))
            return true

        //Diagonal wise
        if(checkmatch(a1,s) && checkmatch(b2,s) && checkmatch(c3,s))
            return true
        if(checkmatch(a3,s) && checkmatch(b2,s) && checkmatch(c1,s))
            return true
        return false
    }


    private fun addToBoard(s: String, it: Button) {
        val button=(it as Button)
        if(currentturn == Turn.NOUGHT)
        {
            checklist[button]= NOUGHT
            button.text = NOUGHT
            button.setTextColor(resources.getColor(R.color.cross_color))
            currentturn = Turn.CROSS
            speak("You selected $s")
            if(checkforWinning(NOUGHT))
            {
                gameInProgress = false
                val handler = Handler()
                speak("Congratulations $player you are Winner")
                handler.postDelayed({
                    gotomain()
                }, 4000)
            }
        }
        if(currentturn == Turn.CROSS)
        {
            botMakeMoveDelayed()
            currentturn = Turn.NOUGHT
        }
    }

    private fun botMakeMoveDelayed() {
        val handler = Handler()
        handler.postDelayed({
            botMakeMove()
        }, 3000)
    }

    private fun botMakeMove() {
        if(gameInProgress) {
            if(boturn%2==0) {

                var bestScore = -800
                var bestMove: Button? = null

                for ((item, value) in checklist) {
                    if (value == "") {
                        checklist[item] = CROSS
                        var score = minmax(checklist, 0, false)
                        checklist[item] = ""
                        if (score > bestScore) {
                            bestMove = item
                            bestScore = score
                        }
                    }
                }
                if (bestMove != null) {
                    checklist[bestMove] = CROSS
                    bestMove.text = CROSS
                    bestMove.setTextColor(resources.getColor(R.color.nought_color))
                    speak("Bot selected ${putlist[bestMove]} as ${bestMove.text}")
                    if (checkforWinning(CROSS)) {
                        val handler = Handler()
                        gameInProgress = false
                        speak("Better luck next time")
                        handler.postDelayed({
                            gotomain()
                        }, 2000)
                    } else if (checkdraw()) {
                        val handler = Handler()
                        gameInProgress = false
                        speak("Oh it's a Draw")
                        handler.postDelayed({
                            gotomain()
                        }, 2000)
                    } else {
                        val handler = Handler()
                        handler.postDelayed({
                            speak("$player's turn")
                        }, 3000)
                    }
                }
            }
            else{
                val dict = mutableMapOf<Button, String>()
                dict[a1] = "a1"
                dict[a2] = "a2"
                dict[a3] = "a3"
                dict[b1] = "b1"
                dict[b2] = "b2"
                dict[b3] = "b3"
                dict[c1] = "c1"
                dict[c2] = "c2"
                dict[c3] = "c3"
                val emptyButtons =
                    listOf(a1, a2, a3, b1, b2, b3, c1, c2, c3).filter { it.text.isEmpty() }
                if (emptyButtons.isNotEmpty()) {
                    val randomIndex = (0 until emptyButtons.size).random()
                    val botMove = emptyButtons[randomIndex]
                    checklist[botMove] = CROSS
                    botMove.text = Easy_Blind_Mode.CROSS
                    botMove.setTextColor(resources.getColor(R.color.nought_color))
                    speak("Bot selected ${dict[botMove]} as ${botMove.text}")
                    if (checkforWinning(Easy_Blind_Mode.CROSS)) {
                        val handler = Handler()
                        gameInProgress = false
                        speak("Better luck next time")
                        handler.postDelayed({
                            gotomain()
                        }, 2000)
                    } else if (checkdraw()) {
                        val handler = Handler()
                        gameInProgress = false
                        speak("Oh it's a Draw")
                        handler.postDelayed({
                            gotomain()
                        }, 2000)
                    } else {
                        val handler = Handler()
                        handler.postDelayed({
                            speak("$player's turn")
                        }, 2000)
                    }
                }
            }
            boturn++
        }
    }

    private fun minmax(checklist: MutableMap<Button, String>, depth: Int, isMaximizing: Boolean): Int {
        if (checkforWin(NOUGHT, checklist)) {
            return -1
        } else if (checkforWin(CROSS, checklist)) {
            return 1
        }else if(checkdrawornot(checklist)){
            return 0
        }

        if (isMaximizing) {
            var bestScore = -800
            for ((item, value) in checklist) {
                if (value == "") {
                    checklist[item] = CROSS
                    var score = minmax(checklist, depth + 1, false)
                    checklist[item] = ""
                    bestScore = maxOf(bestScore, score)
                }
            }
            return bestScore
        } else {
            var bestScore = 800
            for ((item, value) in checklist) {
                if (value == "") {
                    checklist[item] = NOUGHT
                    var score = minmax(checklist, depth + 1, true)
                    checklist[item] = ""
                    bestScore = minOf(bestScore, score)
                }
            }
            return bestScore
        }
    }

    private fun checkdrawornot(checklist: MutableMap<Button, String>): Boolean {
        if(checklist[a1]!="" && checklist[a2]!="" && checklist[a3]!="" && checklist[b1]!="" && checklist[b2]!="" && checklist[b3]!="" && checklist[c1]!="" && checklist[c2]!="" && checklist[c3]!="")
            return true
        return false
    }

    private fun checkforWin(value: String, checklist: MutableMap<Button, String>): Boolean {
        if(checklist[a1]==value && checklist[a2]==value && checklist[a3]==value)
            return true
        if(checklist[b1]==value && checklist[b2]==value && checklist[b3]==value)
            return true
        if(checklist[c1]==value && checklist[c2]==value && checklist[c3]==value)
            return true
        if(checklist[a1]==value && checklist[b1]==value && checklist[c1]==value)
            return true
        if(checklist[a2]==value && checklist[b2]==value && checklist[c2]==value)
            return true
        if(checklist[a3]==value && checklist[b3]==value && checklist[c3]==value)
            return true
        if(checklist[a1]==value && checklist[b2]==value && checklist[c3]==value)
            return true
        if(checklist[a3]==value && checklist[b2]==value && checklist[c1]==value)
            return true
        return false
    }


    private fun present(button: Button): String {
        return when (button.text) {
            NOUGHT -> NOUGHT
            CROSS -> CROSS
            else -> ""
        }
    }

    private fun checkVal(s: String,it: View?) {
        val text=(it as Button).text
        when (text) {
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

    private fun speak(message: String) {
        if (tts.isSpeaking) {
            tts.stop()
        }
        // Speak the message
        tts.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // Set language and other configurations if needed
            tts.language = Locale.US

            Toast.makeText(this, "TextToSpeech initialization success", Toast.LENGTH_SHORT).show()
            // You can speak a default message here if required
            speak("Bot will make a move")
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

    companion object{
        const val NOUGHT="O"
        const val CROSS="X"
    }
}