package com.favoratti.harmonicashortcut.composable.song

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.favoratti.harmonicashortcut.composable.KeyComposable
import com.favoratti.harmonicashortcut.data.Data
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme

object SongKeySelectorComposable {

    @Composable
    fun SongKeySelector(
        selectedKeyState: State<String>,
        onKeyClick: (String) -> Unit
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(74.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Keys(isMinor = false, selectedKey = selectedKeyState, onKeyClick = onKeyClick)
                    Keys(isMinor = true, selectedKey = selectedKeyState, onKeyClick = onKeyClick)
                }
            }
        }
    }

    @Composable
    fun Keys(
        isMinor: Boolean = false,
        selectedKey: State<String>,
        onKeyClick: (String) -> Unit,
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Data.keys.forEach { key ->
                val keyMajorOrMinor = if (isMinor) {
                    "${key}m"
                } else {
                    key
                }
                KeyComposable.KeySelection(
                    selectedKey = selectedKey,
                    key = keyMajorOrMinor,
                    onKeyClick = onKeyClick
                )
            }
        }
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
fun SongKeySelectorPreview() {
    HarmonicaShortcutTheme {
        val selectedKey = remember { mutableStateOf("C") }
        SongKeySelectorComposable.SongKeySelector(selectedKeyState = selectedKey) {
            // do nothing
        }
    }
}