package com.favoratti.harmonicashortcut.composable.util

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.favoratti.arclayout.Alignment
import com.favoratti.arclayout.ArcLayout
import com.favoratti.harmonicashortcut.composable.KeyCircleSelection
import com.favoratti.harmonicashortcut.data.Data
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme

@Composable
fun HarmonicaArcLayout(
    selectedKey: State<String>,
    onKeySelectionClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        ArcLayout(alignment = Alignment.BOTTOM, radius = 150.dp) {
            Data.keys.forEach { key ->
                KeyCircleSelection(
                    selectedKey = selectedKey,
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
fun HarmonicaArcLayoutPreview() {
    HarmonicaShortcutTheme {
        val selectedKey = remember { mutableStateOf("C") }
        HarmonicaArcLayout(
            selectedKey = selectedKey
        ) {
            // do nothing
        }
    }
}