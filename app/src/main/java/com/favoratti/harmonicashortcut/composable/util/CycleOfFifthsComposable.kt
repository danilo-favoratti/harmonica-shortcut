package com.favoratti.harmonicashortcut.composable.util

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.favoratti.harmonicashortcut.R
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme

object CycleOfFifthsComposable {

    @Composable
    fun CycleOfFifths() {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.cycle_of_fifths), // Replace with the actual image resource
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 8.dp),
                    contentScale = ContentScale.FillWidth
                )
            }
        }
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
fun CycleOfFifthsPreview() {
    HarmonicaShortcutTheme {
        CycleOfFifthsComposable.CycleOfFifths()
    }
}