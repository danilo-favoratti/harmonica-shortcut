package com.favoratti.harmonicashortcut.composable.util

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.favoratti.harmonicashortcut.composable.Title
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme

@Composable
fun HarmonicaCardTitle(
    title: String,
    containerColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
    paddingBottom: Dp = 0.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Row {
        Title(
            text = title
        )
    }

    Row {
        HarmonicaCard(
            containerColor, paddingBottom, content
        )
    }
}

@Composable
fun HarmonicaCard(
    containerColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
    paddingBottom: Dp = 0.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = paddingBottom),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),

        ) {
        Column(
            modifier = Modifier.padding(8.dp),
            content = content
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
fun HarmonicaCardTitlePreview() {
    HarmonicaShortcutTheme {
        HarmonicaCardTitle(
            title = "Test Title",
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {

            }
        }
    }
}