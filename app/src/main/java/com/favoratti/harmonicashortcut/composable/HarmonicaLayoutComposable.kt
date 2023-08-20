package com.favoratti.harmonicashortcut.composable

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.favoratti.harmonicashortcut.composable.util.HarmonicaCard
import com.favoratti.harmonicashortcut.data.Data
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme
import com.favoratti.harmonicashortcut.ui.theme.customColorScheme

@Composable
fun HarmonicaLayout(
    keyState: State<String>,
    paddingValues: PaddingValues = PaddingValues(horizontal = 16.dp),
    positionMapState: State<Map<Int, ArrayList<Boolean>>?>? = null,
    showNumbers: Boolean = false,
    onKeyLayoutSelection: (String, Int, Int) -> String,
    onKeyLayoutHighlight: ((Int, Int, Map<Int, ArrayList<Boolean>>?) -> Boolean)? = null
) {
    if (keyState.value.isNotEmpty()) {
        HarmonicaCard(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            paddingValues = paddingValues
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                for (row in 0..6) {
                    Row {
                        for (column in 0..9) {
                            var backgroundColor =
                                when (row) {
                                    in listOf(0, 1), in listOf(4, 5, 6) ->
                                        MaterialTheme.colorScheme.primary

                                    in listOf(2, 3) ->
                                        MaterialTheme.colorScheme.onSecondaryContainer

                                    else -> MaterialTheme.colorScheme.primary
                                }

                            val highlightFontColor =
                                onKeyLayoutHighlight?.let {
                                    if (onKeyLayoutHighlight(
                                            row,
                                            column,
                                            positionMapState?.value
                                        )
                                    ) {
                                        backgroundColor = MaterialTheme.customColorScheme.highlight
                                        MaterialTheme.customColorScheme.onHighlight
                                    } else {
                                        MaterialTheme.colorScheme.onPrimary
                                    }
                                } ?: MaterialTheme.colorScheme.onPrimary

                            // show numbers with bends or notes
                            val note = onKeyLayoutSelection(keyState.value, row, column)
                            val text = if (showNumbers) {
                                if (note.isEmpty()) {
                                    ""
                                } else {
                                    if (row > 2) {
                                        "-"
                                    } else {
                                        ""
                                    } + "${column + 1}" +
                                            when (row) {
                                                1, 4 -> "'"
                                                0, 5 -> "''"
                                                6 -> "'''"
                                                else -> ""
                                            }
                                }
                            } else {
                                note
                            }

                            Key(
                                text = text,
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