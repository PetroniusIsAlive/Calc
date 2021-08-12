package com.kras.clcdude

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var hasNum: Boolean = false
    var hasDecimal: Boolean = false

    fun buttonAction(view: View) {
        val iView = findViewById<TextView>(R.id.tvInput).append((view as Button).text)
        hasNum = true


    }

    fun clearNums(view: View) {
        val iView = findViewById<TextView>(R.id.tvInput)
        iView.text = ""
        hasNum = false
        hasDecimal = false
    }

    fun decimalConfig(view: View) {
        if (hasNum && !hasDecimal) {
            val iView = findViewById<TextView>(R.id.tvInput).append(".")
            hasDecimal = true
            hasNum = true
        }
    }

    fun onEqual(view: View) {
        if (hasNum) {
            var viewContent = findViewById<TextView>(R.id.tvInput).text.toString()
            var prefix = ""

            try {
                if (viewContent.startsWith("-")) {
                    prefix = "-"
                    viewContent = viewContent.substring(1)

                }


                if (findViewById<TextView>(R.id.tvInput).text.contains("-")) {
                    val splitValue = viewContent.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    findViewById<TextView>(R.id.tvInput).text =
                        removeZeroIFFullNumber((one.toDouble() - two.toDouble()).toString())
                } else if (findViewById<TextView>(R.id.tvInput).text.contains("+")) {
                    val splitValue = viewContent.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    findViewById<TextView>(R.id.tvInput).text =
                        removeZeroIFFullNumber((one.toDouble() + two.toDouble()).toString())
                } else if (findViewById<TextView>(R.id.tvInput).text.contains("*")) {
                    val splitValue = viewContent.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    findViewById<TextView>(R.id.tvInput).text =
                        removeZeroIFFullNumber((one.toDouble() * two.toDouble()).toString())

                } else if (findViewById<TextView>(R.id.tvInput).text.contains("/")) {
                    val splitValue = viewContent.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    findViewById<TextView>(R.id.tvInput).text =
                        removeZeroIFFullNumber((one.toDouble() / two.toDouble()).toString())
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroIFFullNumber(str: String): String {
        if (str.contains(".0"))
            return str.substring(0, str.length - 2)
        else {
            return str
        }
    }

    fun onOperator(view: View) {
        if (hasNum && !isOperatorAdded(findViewById<TextView>(R.id.tvInput).text.toString())) {
            findViewById<TextView>(R.id.tvInput).append((view as Button).text)
            hasNum = false
            hasDecimal = false
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") ||
                    value.contains("*") ||
                    value.contains("+") ||
                    value.contains("-")
        }

    }

}