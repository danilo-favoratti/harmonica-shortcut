package com.favoratti.harmonicashortcut.composable.scales

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.favoratti.harmonicashortcut.data.Data
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme

object ScalesComposable {

    @Composable
    fun Scales() {
        @Composable
        fun String.getAnnotatedPrimary() = buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                append(this@getAnnotatedPrimary)
            }
        }

        @Composable
        fun String.getAnnotatedSecondary() = buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
                append(this@getAnnotatedSecondary)
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            RowScaleTitle(text = "1st position - Major Pentatonic")
            RowScaleNotAnnotated(text = Data.firstPositionScale)
            RowScaleTitle(text = "2nd position - Major Pentatonic")
            RowScaleNotAnnotated(text = Data.secondPositionScale)
            RowScaleTitle(text = "2nd position - Major Pentatonic - extra bends")
            RowScaleNotAnnotated(text = Data.secondPositionScaleExtraBends)
            /*RowScale(annotatedText = buildAnnotatedString {
                append("-1 2 -2 -3’’ -3’ -3 ".getAnnotatedPrimary())
                append("-4’ ".getAnnotatedSecondary())
                append("-4 5 6 ".getAnnotatedPrimary())
                append("-6’ ".getAnnotatedSecondary())
                append("-6 -7 -8 ".getAnnotatedPrimary())
                append("8’ ".getAnnotatedSecondary())
                append("8 9 -10".getAnnotatedPrimary())
            })*/
            RowScaleTitle(text = "3rd position")
            RowScaleNotAnnotated(text = Data.thirdPositionScale)
        }
    }

    @Composable
    fun RowScaleTitle(
        text: String
    ) {
        RowScale(
            textStyle = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Normal),
            annotatedText = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                    append(text)
                }
            }
        )
    }

    @Composable
    fun RowScaleNotAnnotated(
        text: String
    ) {
        ScaleComposable.Scale(scale = text)
    }

    @Composable
    fun RowScale(
        textStyle: TextStyle = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
        annotatedText: AnnotatedString
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .height(20.dp)
                .fillMaxWidth()
        ) {
            Text(
                style = textStyle,
                text = annotatedText
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
fun ScalesComposablePreview() {
    HarmonicaShortcutTheme {
        ScalesComposable.Scales()
    }
}