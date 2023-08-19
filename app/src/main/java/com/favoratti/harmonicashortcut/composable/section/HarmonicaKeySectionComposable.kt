package com.favoratti.harmonicashortcut.composable.section

import androidx.compose.runtime.Composable
import com.favoratti.harmonicashortcut.composable.harmonica.HarmonicaKeySelector
import com.favoratti.harmonicashortcut.composable.harmonica.HarmonicaLayout
import com.favoratti.harmonicashortcut.composable.harmonica.HarmonicaPositionDisplay
import com.favoratti.harmonicashortcut.composable.util.HarmonicaCardTitle
import com.favoratti.harmonicashortcut.viewmodel.HarmonicaKeyViewModel
import com.favoratti.harmonicashortcut.viewmodel.HarmonicaLayoutViewModel

@Composable
fun HarmonicaKeySection(
    harmonicaKeyViewModel: HarmonicaKeyViewModel,
    harmonicaLayoutViewModel: HarmonicaLayoutViewModel
) {
    HarmonicaCardTitle(
        title = "Harmonica Key"
    ) {
        HarmonicaKeySelector(
            harmonicaKey = harmonicaKeyViewModel.selectedKeyState,
            onKeySelectionClick = harmonicaKeyViewModel::onKeySelectionClick
        )

        HarmonicaPositionDisplay(
            scaleState = harmonicaKeyViewModel.scaleState,
            scaleKeyState = harmonicaKeyViewModel.scaleKeyState,
            firstPosition = harmonicaKeyViewModel.firstPositionState,
            secondPosition = harmonicaKeyViewModel.secondPositionState,
            thirdPosition = harmonicaKeyViewModel.thirdPositionState,
            fourthPosition = harmonicaKeyViewModel.fourthPositionState,
            fifthPosition = harmonicaKeyViewModel.fifthPositionState,
            twelfthPosition = harmonicaKeyViewModel.twelfthPositionState,
            onKeyScaleSelectionClick = harmonicaKeyViewModel::onKeyScaleSelectionClick
        )

        HarmonicaLayout(
            keyState = harmonicaKeyViewModel.selectedKeyState,
            positionMapState = harmonicaKeyViewModel.positionMapState,
            onKeyLayoutSelection = harmonicaLayoutViewModel::onKeyLayoutSelection,
            onKeyLayoutHighlight = harmonicaLayoutViewModel::onKeyLayoutHighlight
        )
    }
}