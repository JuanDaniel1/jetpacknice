package com.example.calculadorajet.calculator.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.calculadorajet.calculator.utils.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    inputText: String,
    outputText: String,
    height: Dp
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = inputText) {
        scope.launch {
            delay(100)
            scrollState.scrollTo(Int.MAX_VALUE)
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().horizontalScroll(scrollState),
            horizontalAlignment = Alignment.End,
        ) {
            BasicTextField(value = inputText, onValueChange = { },
            enabled = true,
            singleLine = true,
            textStyle = TextStyle(
                fontSize = height.sp() * 0.2,
                textAlign = TextAlign.End
            ),
            )
            Text(
                text = outputText,
                style = TextStyle(
                    color = Color.DarkGray,
                    fontSize = height.sp() * 0.27
                ),
                softWrap = false,
                maxLines = 1
            )
        }
    }
}