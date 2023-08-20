package com.favoratti.harmonicashortcut.composable.section

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.favoratti.harmonicashortcut.composable.harmonica.HarmonicaKeySelector
import com.favoratti.harmonicashortcut.composable.HarmonicaLayout
import com.favoratti.harmonicashortcut.composable.harmonica.HarmonicaPositionDisplay
import com.favoratti.harmonicashortcut.composable.scales.Scale
import com.favoratti.harmonicashortcut.composable.util.HarmonicaCardTitle
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme
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
            scaleKeyState = harmonicaKeyViewModel.scaleKeyState,
            firstPosition = harmonicaKeyViewModel.firstPositionState,
            secondPosition = harmonicaKeyViewModel.secondPositionState,
            thirdPosition = harmonicaKeyViewModel.thirdPositionState,
            fourthPosition = harmonicaKeyViewModel.fourthPositionState,
            fifthPosition = harmonicaKeyViewModel.fifthPositionState,
            twelfthPosition = harmonicaKeyViewModel.twelfthPositionState,
            onKeyScaleSelectionClick = harmonicaKeyViewModel::onKeyScaleSelectionClick
        )

        Scale(scale = harmonicaKeyViewModel.scaleState.value)

        HarmonicaLayout(
            showNumbers = true,
            keyState = harmonicaKeyViewModel.selectedKeyState,
            positionMapState = harmonicaKeyViewModel.positionMapState,
            paddingValues = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            onKeyLayoutSelection = harmonicaLayoutViewModel::onKeyLayoutSelection,
            onKeyLayoutHighlight = harmonicaLayoutViewModel::onKeyLayoutHighlight
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
fun HarmonicaKeySectionPreview() {
    HarmonicaShortcutTheme {
        HarmonicaKeySection(
            harmonicaKeyViewModel = HarmonicaKeyViewModel(),
            harmonicaLayoutViewModel = HarmonicaLayoutViewModel()
        )
    }
}