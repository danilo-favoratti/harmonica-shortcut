package com.favoratti.harmonicashortcut.composable.harmonica

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.favoratti.harmonicashortcut.composable.KeySelection
import com.favoratti.harmonicashortcut.data.Data
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme

@Composable
fun HarmonicaKeySelector(
    harmonicaKey: State<String>,
    onKeySelectionClick: (String) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Data.keys.forEach { key ->
                KeySelection(
                    selectedKey = harmonicaKey,
                    key = key,
                    onKeyClick = onKeySelectionClick
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
fun HarmonicaKeySelectorPreview() {
    HarmonicaShortcutTheme {
        val selectedKey = remember { mutableStateOf("C") }
        HarmonicaKeySelector(harmonicaKey = selectedKey) {
            // do nothing
        }
    }
}