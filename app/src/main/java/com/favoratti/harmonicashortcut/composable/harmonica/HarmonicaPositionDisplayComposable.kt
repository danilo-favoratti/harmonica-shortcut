package com.favoratti.harmonicashortcut.composable.harmonica

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.favoratti.harmonicashortcut.composable.KeyPosition
import com.favoratti.harmonicashortcut.composable.scales.Scale
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme

@Composable
fun HarmonicaPositionDisplay(
    scaleState: State<String>,
    scaleKeyState: State<String>,
    firstPosition: State<String>,
    secondPosition: State<String>,
    thirdPosition: State<String>,
    fourthPosition: State<String>,
    fifthPosition: State<String>,
    twelfthPosition: State<String>,
    onKeyScaleSelectionClick: (String, Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            PositionCard(modifier = Modifier.weight(1f)) {
                TextHarmonicaPosition(text = "1st")
                KeysHarmonicaPosition(
                    onKeyScaleSelectionClick = onKeyScaleSelectionClick,
                    scaleKeyState = scaleKeyState,
                    keyState = firstPosition,
                    positionPair = Pair(1, 4),
                    keyStateRelative = fourthPosition
                )
            }
            PositionCard(modifier = Modifier.weight(1f)) {
                TextHarmonicaPosition(text = "2nd")
                KeysHarmonicaPosition(
                    onKeyScaleSelectionClick = onKeyScaleSelectionClick,
                    scaleKeyState = scaleKeyState,
                    keyState = secondPosition,
                    positionPair = Pair(2, 5),
                    keyStateRelative = fifthPosition
                )
            }
            PositionCard(modifier = Modifier.weight(1f)) {
                TextHarmonicaPosition(text = "3rd")
                KeysHarmonicaPosition(
                    onKeyScaleSelectionClick = onKeyScaleSelectionClick,
                    scaleKeyState = scaleKeyState,
                    keyState = thirdPosition,
                    positionPair = Pair(3, 12),
                    keyStateRelative = twelfthPosition
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Scale(scale = scaleState.value)
        }

    }
}

@Composable
fun KeysHarmonicaPosition(
    scaleKeyState: State<String>,
    keyState: State<String>,
    keyStateRelative: State<String>,
    positionPair: Pair<Int, Int>,
    onKeyScaleSelectionClick: (String, Int) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            KeyPosition(
                selectedKey = scaleKeyState,
                key = keyState.value,
                position = positionPair.first,
                onKeyScaleSelectionClick = onKeyScaleSelectionClick
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            KeyPosition(
                selectedKey = scaleKeyState,
                key = keyStateRelative.value,
                position = positionPair.second,
                onKeyScaleSelectionClick = onKeyScaleSelectionClick
            )
        }
    }
}

@Composable
fun PositionCard(
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    modifier: Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        content = content
    )
}

@Composable
fun TextHarmonicaPosition(
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
    text: String
) {
    Box(modifier = Modifier.padding(8.dp)) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = textStyle.copy(textAlign = TextAlign.Center),
            text = text
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark",
    showBackground = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight",
    showBackground = true
)
@Composable
fun PositionDisplayPreview() {
    HarmonicaShortcutTheme {
        val scaleState = remember { mutableStateOf("-1, -2") }
        val scaleKeyState = remember { mutableStateOf("C") }
        val firstPosition = remember { mutableStateOf("C") }
        val secondPosition = remember { mutableStateOf("G") }
        val thirdPosition = remember { mutableStateOf("Dm") }
        val fourthPosition = remember { mutableStateOf("Am") }
        val fifthPosition = remember { mutableStateOf("Em") }
        val twelfthPosition = remember { mutableStateOf("F") }

        HarmonicaPositionDisplay(
            scaleState = scaleState,
            scaleKeyState = scaleKeyState,
            firstPosition = firstPosition,
            secondPosition = secondPosition,
            thirdPosition = thirdPosition,
            fourthPosition = fourthPosition,
            fifthPosition = fifthPosition,
            twelfthPosition = twelfthPosition
        ) { _, _ ->
            // do nothing
        }
    }
}