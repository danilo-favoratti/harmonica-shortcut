package com.favoratti.harmonicashortcut.composable.song

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.favoratti.harmonicashortcut.composable.KeyPosition
import com.favoratti.harmonicashortcut.composable.scales.Scale
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme

@Composable
fun SongPositionDisplay(
    scaleState: State<String>,
    scaleKeyState: State<String>,
    firstPosition: State<String>,
    secondPosition: State<String>,
    thirdPosition: State<String>,
    onKeyScaleSelectionClick: (String, Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                TextSongPosition(text = "1st position")
                KeysSongPosition(
                    scaleKeyState = scaleKeyState,
                    keyState = firstPosition,
                    position = 1,
                    onKeyScaleSelectionClick = onKeyScaleSelectionClick
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                TextSongPosition(text = "2nd position")
                KeysSongPosition(
                    scaleKeyState = scaleKeyState,
                    keyState = secondPosition,
                    position = 2,
                    onKeyScaleSelectionClick = onKeyScaleSelectionClick
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                TextSongPosition(text = "3rd position")
                KeysSongPosition(
                    scaleKeyState = scaleKeyState,
                    keyState = thirdPosition,
                    position = 3,
                    onKeyScaleSelectionClick = onKeyScaleSelectionClick
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Scale(scale = scaleState.value)
        }
    }
}

@Composable
fun KeysSongPosition(
    scaleKeyState: State<String>,
    keyState: State<String>,
    position: Int,
    onKeyScaleSelectionClick: (String, Int) -> Unit
) {

    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            KeyPosition(
                selectedKey = scaleKeyState,
                key = keyState.value,
                position = position,
                onKeyScaleSelectionClick = onKeyScaleSelectionClick
            )
        }
    }
}

@Composable
fun TextSongPosition(
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
fun SongPositionDisplayPreview() {
    HarmonicaShortcutTheme {
        val scaleState = remember { mutableStateOf("C") }
        val scaleKeyState = remember { mutableStateOf("C") }
        val firstPosition = remember { mutableStateOf("C") }
        val secondPosition = remember { mutableStateOf("G") }
        val thirdPosition = remember { mutableStateOf("Dm") }

        SongPositionDisplay(
            scaleState = scaleState,
            scaleKeyState = scaleKeyState,
            firstPosition = firstPosition,
            secondPosition = secondPosition,
            thirdPosition = thirdPosition
        ) { _, _ ->
            // do nothing
        }
    }
}