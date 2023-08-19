package com.favoratti.harmonicashortcut.composable

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme

@Composable
fun KeySelection(
    selectedKey: State<String>,
    key: String,
    onKeyClick: (String) -> Unit
) {
    KeyLayout(selectedKey = selectedKey, key = key) {
        onKeyClick.invoke(key)
    }
}

@Composable
fun KeyPosition(
    selectedKey: State<String>,
    key: String,
    position: Int,
    onKeyScaleSelectionClick: (String, Int) -> Unit
) {
    KeyLayout(selectedKey = selectedKey, key = key) {
        onKeyScaleSelectionClick.invoke(key, position)
    }
}

@Composable
fun KeyLayout(
    selectedKey: State<String>,
    key: String,
    onClick: () -> Unit
) {
    val backgroundColor = if (selectedKey.value == key) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }

    val fontColor = if (selectedKey.value == key) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSecondary
    }

    Key(key, backgroundColor, fontColor, onClick)
}

@Composable
fun Key(
    key: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    fontColor: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit = { }
) {
    if (key.isEmpty()) {
        Box(
            modifier = Modifier
                .background(Color.Transparent)
                .width(28.dp)
                .height(28.dp)
        )
    } else {
        Box(
            modifier = Modifier
                .background(backgroundColor)
                .width(28.dp)
                .height(28.dp)
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant)
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.labelMedium.copy(color = fontColor),
                text = key
            )
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
fun KeyComposablePreview() {
    HarmonicaShortcutTheme {
        val key = remember { mutableStateOf("C") }
        KeySelection(
            selectedKey = key,
            key = "C"
        ) {
            // do  nothing
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
fun KeyComposableNotSelectedPreview() {
    HarmonicaShortcutTheme {
        val key = remember {
            mutableStateOf("C")
        }
        KeySelection(
            selectedKey = key,
            key = "D"
        ) {
            // do  nothing
        }
    }
}
