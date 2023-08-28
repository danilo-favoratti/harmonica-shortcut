package com.favoratti.harmonicashortcut.composable

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme

@Composable
fun KeyCircleSelection(
    selectedKey: State<String>,
    key: String,
    onKeyClick: (String) -> Unit
) {
    KeyCircleLayout(selectedKey = selectedKey, key = key) {
        onKeyClick.invoke(key)
    }
}

@Composable
fun KeyCirclePosition(
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
fun KeyCircleLayout(
    selectedKey: State<String>,
    key: String,
    onClick: () -> Unit
) {
    val backgroundColor = if (selectedKey.value == key) {
        MaterialTheme.colorScheme.tertiaryContainer
    } else {
        MaterialTheme.colorScheme.primaryContainer
    }

    val fontColor = if (selectedKey.value == key) {
        MaterialTheme.colorScheme.onTertiaryContainer
    } else {
        MaterialTheme.colorScheme.onPrimaryContainer
    }

    KeyCircle(
        text = key,
        backgroundColor = backgroundColor,
        fontColor = fontColor,
        onClick = onClick
    )
}

@Composable
fun KeyCircle(
    text: String,
    number: String? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    fontColor: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit = { }
) {
    if (text.isEmpty()) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp)
                .background(Color.Transparent)
        )
    } else {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp)
                .background(backgroundColor)
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            val keyText = number?.let {
                "$number:$text"
            } ?: text
            Text(
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.labelMedium.copy(color = fontColor),
                text = keyText
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
fun KeyCircleComposablePreview() {
    HarmonicaShortcutTheme {
        val key = remember { mutableStateOf("C") }
        KeyCircleSelection(
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
fun KeyCircleComposableNotSelectedPreview() {
    HarmonicaShortcutTheme {
        val key = remember {
            mutableStateOf("C")
        }
        KeyCircleSelection(
            selectedKey = key,
            key = "D"
        ) {
            // do  nothing
        }
    }
}
