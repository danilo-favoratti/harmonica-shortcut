package com.favoratti.harmonicashortcut.composable.section

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.favoratti.harmonicashortcut.composable.harmonica.HarmonicaKeySelectorForImage
import com.favoratti.harmonicashortcut.composable.harmonica.HarmonicaLayout
import com.favoratti.harmonicashortcut.composable.util.HarmonicaCardTitle
import com.favoratti.harmonicashortcut.viewmodel.HarmonicaLayoutViewModel

@Composable
fun HarmonicaLayoutSection(harmonicaLayoutViewModel: HarmonicaLayoutViewModel) {
    HarmonicaCardTitle(
        title = "Harmonica Layout",
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    ) {
        HarmonicaKeySelectorForImage(
            keys = harmonicaLayoutViewModel.getKeys(),
            selectedKey = harmonicaLayoutViewModel.keyState,
            onKeyClick = harmonicaLayoutViewModel::onKeyClick
        )

        HarmonicaLayout(
            keyState = harmonicaLayoutViewModel.keyState,
            onKeyLayoutSelection = harmonicaLayoutViewModel::onKeyLayoutSelection
        )
    }
}