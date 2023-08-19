package com.favoratti.harmonicashortcut.composable.section

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.favoratti.harmonicashortcut.composable.util.CycleOfFifths
import com.favoratti.harmonicashortcut.composable.util.HarmonicaCardTitle

@Composable
fun CycleOfFifthsSection() {
    HarmonicaCardTitle(
        title = "Cycle of Fifths",
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    ) {
        CycleOfFifths()
    }
}