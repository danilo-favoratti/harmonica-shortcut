package com.favoratti.harmonicashortcut.composable.section

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.favoratti.harmonicashortcut.composable.util.HarmonicaCardTitle
import com.favoratti.harmonicashortcut.composable.util.UsefulLinks

@Composable
fun LinksSection(openWebLink: (String) -> Unit) {
    HarmonicaCardTitle(
        title = "Links",
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    ) {
        UsefulLinks(openLink = openWebLink)
    }
}