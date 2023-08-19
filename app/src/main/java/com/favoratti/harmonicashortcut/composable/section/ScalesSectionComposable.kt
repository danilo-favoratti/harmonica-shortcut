package com.favoratti.harmonicashortcut.composable.section

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.favoratti.harmonicashortcut.composable.scales.Scales
import com.favoratti.harmonicashortcut.composable.util.HarmonicaCardTitle

@Composable
fun ScalesSection() {
    HarmonicaCardTitle(
        title = "Scales",
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Scales()
    }
}