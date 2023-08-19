package com.favoratti.harmonicashortcut.composable.section

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.favoratti.harmonicashortcut.composable.harmonica.HarmonicaLayout
import com.favoratti.harmonicashortcut.composable.song.SongKeySelector
import com.favoratti.harmonicashortcut.composable.song.SongPositionDisplay
import com.favoratti.harmonicashortcut.composable.util.HarmonicaCardTitle
import com.favoratti.harmonicashortcut.viewmodel.HarmonicaLayoutViewModel
import com.favoratti.harmonicashortcut.viewmodel.SongKeyViewModel

@Composable
fun SongKeySection(
    songKeyViewModel: SongKeyViewModel,
    harmonicaLayoutViewModel: HarmonicaLayoutViewModel
) {
    HarmonicaCardTitle(
        title = "Song Key"
    ) {
        SongKeySelector(
            selectedKeyState = songKeyViewModel.selectedKeyState,
            onKeyClick = songKeyViewModel::onKeyClick
        )

        SongPositionDisplay(
            scaleState = songKeyViewModel.scaleState,
            scaleKeyState = songKeyViewModel.scaleKeyState,
            firstPosition = songKeyViewModel.firstPositionState,
            secondPosition = songKeyViewModel.secondPositionState,
            thirdPosition = songKeyViewModel.thirdPositionState,
            onKeyScaleSelectionClick = songKeyViewModel::onKeyScaleSelectionClick
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            HarmonicaLayout(
                keyState = songKeyViewModel.scaleKeyState,
                positionMapState = songKeyViewModel.positionMapState,
                onKeyLayoutSelection = harmonicaLayoutViewModel::onKeyLayoutSelection,
                onKeyLayoutHighlight = harmonicaLayoutViewModel::onKeyLayoutHighlight
            )
        }
    }
}