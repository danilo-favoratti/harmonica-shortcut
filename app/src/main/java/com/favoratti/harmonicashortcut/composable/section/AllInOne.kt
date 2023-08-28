package com.favoratti.harmonicashortcut.composable.section

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme
import com.favoratti.harmonicashortcut.viewmodel.HarmonicaKeyViewModel
import com.favoratti.harmonicashortcut.viewmodel.HarmonicaLayoutViewModel
import com.favoratti.harmonicashortcut.viewmodel.SongKeyViewModel

@Composable
fun AllInOne(
    harmonicaKeyViewModel: HarmonicaKeyViewModel,
    songKeyViewModel: SongKeyViewModel,
    harmonicaLayoutViewModel: HarmonicaLayoutViewModel,
    openWebLink: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HarmonicaKeySection(harmonicaKeyViewModel, harmonicaLayoutViewModel)

        SongKeySection(songKeyViewModel, harmonicaLayoutViewModel)

        ScalesSection()

        ThirdLicksSection()

        HarmonicaLayoutSection(harmonicaLayoutViewModel)

        CycleOfFifthsSection()

        LinksSection(openWebLink)
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
fun AllInOnePreview() {
    HarmonicaShortcutTheme {
        AllInOne(
            harmonicaKeyViewModel = HarmonicaKeyViewModel(),
            songKeyViewModel = SongKeyViewModel(),
            harmonicaLayoutViewModel = HarmonicaLayoutViewModel()
        ) {
            // do nothing
        }
    }
}