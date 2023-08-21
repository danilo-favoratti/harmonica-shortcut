package com.favoratti.harmonicashortcut

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.favoratti.harmonicashortcut.composable.section.CycleOfFifthsSection
import com.favoratti.harmonicashortcut.composable.section.HarmonicaKeySection
import com.favoratti.harmonicashortcut.composable.section.HarmonicaLayoutSection
import com.favoratti.harmonicashortcut.composable.section.LinksSection
import com.favoratti.harmonicashortcut.composable.section.ScalesSection
import com.favoratti.harmonicashortcut.composable.section.SongKeySection
import com.favoratti.harmonicashortcut.composable.section.ThirdLicksSection
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme
import com.favoratti.harmonicashortcut.viewmodel.HarmonicaKeyViewModel
import com.favoratti.harmonicashortcut.viewmodel.HarmonicaLayoutViewModel
import com.favoratti.harmonicashortcut.viewmodel.SongKeyViewModel

class MainActivity : ComponentActivity() {

    private val mHarmonicaKeyViewModel: HarmonicaKeyViewModel by viewModels()
    private val mSongKeyViewModel: SongKeyViewModel by viewModels()
    private val mHarmonicaLayoutViewModel: HarmonicaLayoutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
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