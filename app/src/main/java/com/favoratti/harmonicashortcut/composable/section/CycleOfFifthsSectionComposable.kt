package com.favoratti.harmonicashortcut.composable.section

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.favoratti.harmonicashortcut.composable.util.CycleOfFifths
import com.favoratti.harmonicashortcut.composable.util.HarmonicaCardTitle
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme

@Composable
fun CycleOfFifthsSection() {
    HarmonicaCardTitle(
        title = "Cycle of Fifths",
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    ) {
        CycleOfFifths()
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
fun CycleOfFifthsSectionPreview() {
    HarmonicaShortcutTheme {
        CycleOfFifthsSection()
    }
}