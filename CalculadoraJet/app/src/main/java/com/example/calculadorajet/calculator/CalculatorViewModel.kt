package com.example.calculadorajet.calculator

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.calculadorajet.calculator.utils.isNumber
import com.example.calculadorajet.calculator.utils.isParent
import org.mozilla.javascript.Context
import java.io.DataInput

class CalculatorViewModel: ViewModel() {
    private val _inputText = mutableStateOf(CalculatorState())
    val inputText: State<CalculatorState> = _inputText

    private var _outputText = mutableStateOf(CalculatorState())
    val outputText: State<CalculatorState> = _outputText

    fun onEvent(e: CalculatorEvent) {
        when (e) {
            CalculatorEvent.AllClear -> allclear()
            CalculatorEvent.BackSpace -> backSpace()
            CalculatorEvent.Calculate -> calculate()
            is CalculatorEvent.Write -> write(e.value)
        }
    }
    private fun allclear() {
        _inputText.value = inputText.value.copy(
            text = ""
        )
        _outputText.value = outputText.value.copy(
            text = ""
        )
    }
    private fun backSpace() {
        val length = _inputText.value.text.length
        if(length > 0) {
            _inputText.value = inputText.value.copy(
                text = inputText.value.text.subSequence(0, length - 1) as String
            )
        }
    }
    private fun calculate() {
        val result = rhinoSetUp(outputText.value.text + inputText.value.text)
        _outputText.value = outputText.value.copy(
            text = result
        )
        _inputText.value = inputText.value.copy("")
    }



    private fun write(value: String) {
        _inputText.value = inputText.value.copy(text = outputText.value.text + inputText.value.text + value)
        _outputText.value = outputText.value.copy("")
    }
    private fun rhinoSetUp(input: String): String{
            val adjustedInput = input.replace("x", "*")
                .replace(Regex("""(\d+)(\()""")) { matchResult ->
                    val number = matchResult.groupValues[1]
                    val previousChar = matchResult.groupValues[2]


                        "$previousChar$number*"



                }

            val rhino = Context.enter()
            rhino.optimizationLevel = -1

            val scriptable = rhino.initStandardObjects()
            return rhino.evaluateString(
                scriptable,
                adjustedInput,
                "javascript",
                1,
                null
            ).toString()
        }
}