package ru.den.kmm.androidApp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.den.kmm.shared.Factorial
import ru.den.kmm.shared.Greeting
//import ru.den.logs.Log4m

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()

        val resultTv: TextView = findViewById(R.id.text_view_result)
        val editText: EditText = findViewById(R.id.edit_text)

        val btn: Button = findViewById(R.id.btn)
        btn.setOnClickListener {
            val text = editText.text.toString()

            if (text.isEmpty()) return@setOnClickListener
            val number = text.tryToLong() ?: return@setOnClickListener

            btn.isEnabled = false

            lifecycleScope.launch {
                val result = Factorial().calc(number)
                withContext(Dispatchers.Main) {
//                    Log4m.d(
//                        message = "set result",
//                        tag = "Factorial"
//                    )
                    resultTv.text = result
                    btn.isEnabled = true
                }
            }
        }
    }
}

fun String.tryToLong(): Long? {
    return try {
        toLong()
    } catch (ex: NumberFormatException) {
        null
    }
}
