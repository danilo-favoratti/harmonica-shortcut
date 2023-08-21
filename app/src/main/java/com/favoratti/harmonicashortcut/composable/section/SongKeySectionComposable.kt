package com.favoratti.harmonicashortcut.composable.section

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.favoratti.harmonicashortcut.composable.HarmonicaLayout
import com.favoratti.harmonicashortcut.composable.scales.Scale
import com.favoratti.harmonicashortcut.composable.song.SongKeySelector
import com.favoratti.harmonicashortcut.composable.song.SongPositionDisplay
import com.favoratti.harmonicashortcut.composable.util.HarmonicaCardTitle
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme
import com.favoratti.harmonicashortcut.viewmodel.HarmonicaLayoutViewModel
import com.favoratti.harmonicashortcut.viewmodel.ShowOnlyNumberSwitch
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
            scaleKeyState = songKeyViewModel.scaleKeyState,
            firstPosition = songKeyViewModel.firstPositionState,
            secondPosition = songKeyViewModel.secondPositionState,
            thirdPosition = songKeyViewModel.thirdPositionState,
            onKeyScaleSelectionClick = songKeyViewModel::onKeyScaleSelectionClick
        )

        Scale(scale = songKeyViewModel.scaleState.value)

        HarmonicaLayout(
            showNumbers = songKeyViewModel.numberNoteState.value,
            keyState = songKeyViewModel.scaleKeyState,
            positionMapState = songKeyViewModel.positionMapState,
            paddingValues = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            onKeyLayoutSelection = harmonicaLayoutViewModel::onKeyLayoutSelection,
            onKeyLayoutHighlight = harmonicaLayoutViewModel::onKeyLayoutHighlight
        )

        ShowOnlyNumberSwitch(
            isVisible = songKeyViewModel.scaleKeyState.value.isNotEmpty(),
            checked = songKeyViewModel.numberNoteState.value,
            onCheckedChange = songKeyViewModel::setNumberNoteState
        )
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
fun SongKeySectionPreview() {
    HarmonicaShortcutTheme {
        SongKeySection(
            songKeyViewModel = SongKeyViewModel(),
            harmonicaLayoutViewModel = HarmonicaLayoutViewModel()
        )
    }
}