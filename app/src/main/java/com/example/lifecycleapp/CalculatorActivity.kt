package com.example.lifecycleapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var operand1 = 0
    private var operand2 = 0
    private var operator: String? = null
    private var isNewOperand = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        display = findViewById(R.id.tvDisplay)

        val numberButtons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )
        numberButtons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                appendNumber((it as Button).text.toString())
            }
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnSub).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnMul).setOnClickListener { setOperator("x") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { setOperator("/") }
        findViewById<Button>(R.id.btnEq).setOnClickListener { calculate() }
        findViewById<Button>(R.id.btnC).setOnClickListener { clearAll() }
        findViewById<Button>(R.id.btnCE).setOnClickListener { clearEntry() }
        findViewById<Button>(R.id.btnBS).setOnClickListener { backspace() }
        findViewById<Button>(R.id.btnNeg).setOnClickListener { toggleSign() }
    }

    private fun appendNumber(num: String) {
        if (isNewOperand) {
            display.text = num
            isNewOperand = false
        } else {
            display.text = display.text.toString() + num
        }
    }

    private fun setOperator(op: String) {
        operand1 = display.text.toString().toInt()
        operator = op
        isNewOperand = true
    }

    private fun calculate() {
        operand2 = display.text.toString().toInt()
        val result = when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "x" -> operand1 * operand2
            "/" -> if (operand2 != 0) operand1 / operand2 else 0
            else -> operand2
        }
        display.text = result.toString()
        isNewOperand = true
    }

    private fun clearAll() {
        operand1 = 0
        operand2 = 0
        operator = null
        display.text = "0"
        isNewOperand = true
    }

    private fun clearEntry() {
        display.text = "0"
        isNewOperand = true
    }

    private fun backspace() {
        val current = display.text.toString()
        display.text = if (current.length > 1) current.dropLast(1) else "0"
    }

    private fun toggleSign() {
        val value = display.text.toString().toInt()
        display.text = (-value).toString()
    }
}
