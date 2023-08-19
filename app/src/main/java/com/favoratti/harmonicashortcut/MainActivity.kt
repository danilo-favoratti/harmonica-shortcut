package com.favoratti.harmonicashortcut

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.favoratti.harmonicashortcut.composable.TitleComposable
import com.favoratti.harmonicashortcut.composable.harmonica.HarmonicaKeySelectorComposable
import com.favoratti.harmonicashortcut.composable.harmonica.HarmonicaKeySelectorForImageComposable
import com.favoratti.harmonicashortcut.composable.harmonica.HarmonicaLayoutComposable
import com.favoratti.harmonicashortcut.composable.harmonica.HarmonicaPositionDisplayComposable
import com.favoratti.harmonicashortcut.composable.scales.ScalesComposable
import com.favoratti.harmonicashortcut.composable.song.SongKeySelectorComposable
import com.favoratti.harmonicashortcut.composable.song.SongPositionDisplayComposable
import com.favoratti.harmonicashortcut.composable.util.CycleOfFifthsComposable
import com.favoratti.harmonicashortcut.composable.util.UsefulLinksComposable
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme
import com.favoratti.harmonicashortcut.viewmodel.HarmonicaKeyViewModel
import com.favoratti.harmonicashortcut.viewmodel.HarmonicaLayoutViewModel
import com.favoratti.harmonicashortcut.viewmodel.SongKeyViewModel

class MainActivity : ComponentActivity() {

    private val mHarmonicaKeyViewModel: HarmonicaKeyViewModel by viewModels()
    private val mSongKeyViewModel: SongKeyViewModel by viewModels()
    private val mHarmonicaLayoutViewModel: HarmonicaLayoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HarmonicaShortcutTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Position(
                        harmonicaKeyViewModel = mHarmonicaKeyViewModel,
                        songKeyViewModel = mSongKeyViewModel,
                        harmonicaLayoutViewModel = mHarmonicaLayoutViewModel,
                        openWebLink = ::openWebLink
                    )
                }
            }
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun openWebLink(url: String) {
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (webIntent.resolveActivity(packageManager) != null) {
            ContextCompat.startActivity(this, webIntent, null)
        }
    }
}

@Composable
fun Position(
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
        HarmonicaCardTitle(
            title = "Harmonica Key"
        ) {
            HarmonicaKeySelectorComposable.HarmonicaKeySelector(
                harmonicaKey = harmonicaKeyViewModel.selectedKeyState,
                onKeySelectionClick = harmonicaKeyViewModel::onKeySelectionClick
            )

            HarmonicaPositionDisplayComposable.HarmonicaPositionDisplay(
                scaleState = harmonicaKeyViewModel.scaleState,
                scaleKeyState = harmonicaKeyViewModel.scaleKeyState,
                firstPosition = harmonicaKeyViewModel.firstPositionState,
                secondPosition = harmonicaKeyViewModel.secondPositionState,
                thirdPosition = harmonicaKeyViewModel.thirdPositionState,
                fourthPosition = harmonicaKeyViewModel.fourthPositionState,
                fifthPosition = harmonicaKeyViewModel.fifthPositionState,
                twelfthPosition = harmonicaKeyViewModel.twelfthPositionState,
                onKeyScaleSelectionClick = harmonicaKeyViewModel::onKeyScaleSelectionClick
            )

            HarmonicaLayoutComposable.HarmonicaLayout(
                keyState = harmonicaKeyViewModel.selectedKeyState,
                positionMapState = harmonicaKeyViewModel.positionMapState,
                onKeyLayoutSelection = harmonicaLayoutViewModel::onKeyLayoutSelection,
                onKeyLayoutHighlight = harmonicaLayoutViewModel::onKeyLayoutHighlight
            )
        }

        HarmonicaCardTitle(
            title = "Song Key"
        ) {
            SongKeySelectorComposable.SongKeySelector(
                selectedKeyState = songKeyViewModel.selectedKeyState,
                onKeyClick = songKeyViewModel::onKeyClick
            )

            SongPositionDisplayComposable.SongPositionDisplay(
                scaleState = songKeyViewModel.scaleState,
                scaleKeyState = songKeyViewModel.scaleKeyState,
                firstPosition = songKeyViewModel.firstPositionState,
                secondPosition = songKeyViewModel.secondPositionState,
                thirdPosition = songKeyViewModel.thirdPositionState,
                onKeyScaleSelectionClick = songKeyViewModel::onKeyScaleSelectionClick
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                HarmonicaLayoutComposable.HarmonicaLayout(
                    keyState = songKeyViewModel.scaleKeyState,
                    positionMapState = songKeyViewModel.positionMapState,
                    onKeyLayoutSelection = harmonicaLayoutViewModel::onKeyLayoutSelection,
                    onKeyLayoutHighlight = harmonicaLayoutViewModel::onKeyLayoutHighlight
                )
            }
        }

        HarmonicaCardTitle(
            title = "Scales",
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ) {
            ScalesComposable.Scales()
        }

        HarmonicaCardTitle(
            title = "Third Licks",
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Image(
                painter = painterResource(id = R.drawable.terceira), // Replace with the actual image resource
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentScale = ContentScale.FillWidth
            )
        }

        HarmonicaCardTitle(
            title = "Harmonica Layout",
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ) {
            HarmonicaKeySelectorForImageComposable.HarmonicaKeySelectorForImage(
                keys = harmonicaLayoutViewModel.getKeys(),
                selectedKey = harmonicaLayoutViewModel.keyState,
                onKeyClick = harmonicaLayoutViewModel::onKeyClick
            )

            HarmonicaLayoutComposable.HarmonicaLayout(
                keyState = harmonicaLayoutViewModel.keyState,
                onKeyLayoutSelection = harmonicaLayoutViewModel::onKeyLayoutSelection
            )
        }

        HarmonicaCardTitle(
            title = "Cycle of Fifths",
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ) {
            CycleOfFifthsComposable.CycleOfFifths()
        }

        HarmonicaCardTitle(
            title = "Links",
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ) {
            UsefulLinksComposable.UsefulLinks(openLink = openWebLink)
        }
    }
}

@Composable
fun HarmonicaCardTitle(
    title: String,
    containerColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
    paddingBottom: Dp = 0.dp,
    content: @Composable ColumnScope.() -> Unit
) {

    TitleComposable.Title(
        text = title
    )

    HarmonicaCard(
        containerColor, paddingBottom, content
    )

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
fun PositionPreview() {
    HarmonicaShortcutTheme {
        Position(
            harmonicaKeyViewModel = HarmonicaKeyViewModel(),
            songKeyViewModel = SongKeyViewModel(),
            harmonicaLayoutViewModel = HarmonicaLayoutViewModel()
        ) {
            // do nothing
        }
    }
}