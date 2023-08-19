package com.favoratti.harmonicashortcut.composable.util

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme

object UsefulLinksComposable {

    @Composable
    fun UsefulLinks(
        openLink: (String) -> Unit
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            RowTitle(text = "Blues Etílicos - Misty Mountain")
            RowLink(text = "https://www.youtube.com/watch?v=yuALfcQUgts", openLink = openLink)

            RowTitle(text = "George Harmonica Smith - Telephone Blues")
            RowLink(text = "https://www.youtube.com/watch?v=jsM3eDYHGqQ", openLink = openLink)

            RowTitle(text = "Elegant Groove Backing Track in B minor")
            RowLink(text = "https://www.youtube.com/watch?v=XdB1NbIU8-o", openLink = openLink)

            RowTitle(text = "Rock Backing Track D Major | 100 BPM")
            RowLink(text = "https://www.youtube.com/watch?v=2EoqzNR68pY", openLink = openLink)

            RowTitle(text = "Soulful Mellow Groove Track Jam in B Minor")
            RowLink(text = "https://www.youtube.com/watch?v=QLiLE_u_7QY", openLink = openLink)

            RowTitle(text = "Notions Music")
            RowLink(
                text = "https://www.notion.so/Gaita-M-sicas-Estudar-b6f770d0f13a46499b6646bdb4b32e7b",
                openLink = openLink
            )

        }
    }

    @Composable
    fun RowTitle(text: String) {
        RowLink(
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            ),
            text = text,
            openLink = { }
        )
    }

    @Composable
    fun RowLink(
        textStyle: TextStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.secondary,
            textDecoration = TextDecoration.Underline,
            fontSize = 12.sp
        ),
        text: String,
        openLink: (String) -> Unit
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .height(20.dp)
                .fillMaxWidth()
        ) {
            ClickableText(
                style = textStyle,
                text = buildAnnotatedString {
                    append(text)
                },
                onClick = {
                    openLink.invoke(text)
                }
            )
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
fun UsefulLinksComposablePreview() {
    HarmonicaShortcutTheme {
        UsefulLinksComposable.UsefulLinks {

        }
    }
}