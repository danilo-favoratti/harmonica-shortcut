package com.favoratti.harmonicashortcut.composable.section

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.favoratti.harmonicashortcut.composable.util.HarmonicaCardTitle
import com.favoratti.harmonicashortcut.composable.util.UsefulLinks
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme

@Composable
fun LinksSection(openWebLink: (String) -> Unit) {
    HarmonicaCardTitle(
        title = "Links",
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    ) {
        UsefulLinks(openLink = openWebLink)
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
fun LinksSectionPreview() {
    HarmonicaShortcutTheme {
        LinksSection {
            // do nothing
        }
    }
}