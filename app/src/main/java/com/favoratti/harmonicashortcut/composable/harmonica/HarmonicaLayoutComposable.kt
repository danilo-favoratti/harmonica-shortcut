package com.favoratti.harmonicashortcut.composable.harmonica

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.favoratti.harmonicashortcut.composable.Key
import com.favoratti.harmonicashortcut.data.Data
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme
import com.favoratti.harmonicashortcut.ui.theme.bend_color
import com.favoratti.harmonicashortcut.ui.theme.key_color
import com.favoratti.harmonicashortcut.ui.theme.key_font_color
import com.favoratti.harmonicashortcut.ui.theme.key_font_color_highlight
import com.favoratti.harmonicashortcut.ui.theme.over_blow_color

@Composable
fun HarmonicaLayout(
    keyState: State<String>,
    positionMapState: State<Map<Int, ArrayList<Boolean>>?>? = null,
    onKeyLayoutSelection: (String, Int, Int) -> String,
    onKeyLayoutHighlight: ((Int, Int, Map<Int, ArrayList<Boolean>>?) -> Boolean)? = null
) {
    if (keyState.value.isNotEmpty()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (line in 0..6) {
                Row {
                    for (row in 0..9) {
                        val backgroundColor =
                            when (line) {
                                in listOf(0, 1) -> over_blow_color
                                in listOf(2, 3) -> key_color
                                else -> bend_color
                            }

                        val highlightFontColor =
                            onKeyLayoutHighlight?.let {
                                if (onKeyLayoutHighlight(line, row, positionMapState?.value)) {
                                    key_font_color_highlight
                                } else {
                                    key_font_color
                                }
                            } ?: key_font_color

                        Key(
                            key = onKeyLayoutSelection(keyState.value, line, row),
                            backgroundColor = backgroundColor,
                            fontColor = highlightFontColor
                        )
                    }
                }
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
fun HarmonicaLayoutPreview() {
    HarmonicaShortcutTheme {
        val keyState = remember { mutableStateOf("C") }
        val positionMapState =
            remember { mutableStateOf(Data.arrayPositionMap[1]) }
        HarmonicaLayout(
            keyState = keyState,
            positionMapState = positionMapState,
            onKeyLayoutSelection = { _, _, _ -> "C" }
        )
    }
}