package com.favoratti.harmonicashortcut.viewmodel

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme

@Composable
fun ShowOnlyNumberSwitch(
    isVisible: Boolean = true,
    checked: Boolean,
    onCheckedChange: () -> Unit
) {
    if (isVisible) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(2f)
                    .align(CenterVertically)
                    .wrapContentWidth(Alignment.Start)
            ) {
                Text(
                    text = "Show only hole numbers"
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            ) {
                Switch(
                    checked = checked,
                    onCheckedChange = { onCheckedChange.invoke() })
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
fun ShowOnlyNumberSwitchPreview() {
    HarmonicaShortcutTheme {
        ShowOnlyNumberSwitch(
            isVisible = true,
            checked = true
        ) {
            // do nothing
        }
    }
}