package com.favoratti.harmonicashortcut.composable.section

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.favoratti.harmonicashortcut.composable.HarmonicaLayout
import com.favoratti.harmonicashortcut.composable.harmonica.HarmonicaKeySelectorForLayout
import com.favoratti.harmonicashortcut.composable.util.HarmonicaCardTitle
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme
import com.favoratti.harmonicashortcut.viewmodel.HarmonicaLayoutViewModel

@Composable
fun HarmonicaLayoutSection(
    harmonicaLayoutViewModel: HarmonicaLayoutViewModel,
    showGreenMessage: Boolean = true
) {
    HarmonicaCardTitle(
        title = "Harmonica Layout",
    ) {
        HarmonicaKeySelectorForLayout(
            keys = harmonicaLayoutViewModel.getKeys(),
            selectedKey = harmonicaLayoutViewModel.keyState,
            onKeyClick = harmonicaLayoutViewModel::onKeyClick
        )

        HarmonicaLayout(
            paddingValues = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            keyState = harmonicaLayoutViewModel.keyState,
            onKeyLayoutSelection = harmonicaLayoutViewModel::onKeyLayoutSelection,
            showGreenMessage = showGreenMessage
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
fun HarmonicaLayoutSectionPreview() {
    HarmonicaShortcutTheme {
        HarmonicaLayoutSection(
            harmonicaLayoutViewModel = HarmonicaLayoutViewModel()
        )
    }
}