package com.favoratti.harmonicashortcut.composable.harmonica

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.favoratti.harmonicashortcut.composable.KeyComposable
import com.favoratti.harmonicashortcut.ui.theme.bend_color
import com.favoratti.harmonicashortcut.ui.theme.key_color
import com.favoratti.harmonicashortcut.ui.theme.key_font_color
import com.favoratti.harmonicashortcut.ui.theme.key_font_color_highlight
import com.favoratti.harmonicashortcut.ui.theme.over_blow_color

object HarmonicaLayoutComposable {

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

                            KeyComposable.Key(
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
}