package com.favoratti.harmonicashortcut

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.favoratti.harmonicashortcut.composable.layout.BottomNavigationCompose
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
                    BottomNavigationCompose(
                        harmonicaKeyViewModel = mHarmonicaKeyViewModel,
                        harmonicaLayoutViewModel = mHarmonicaLayoutViewModel,
                        songKeyViewModel = mSongKeyViewModel,
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
fun MainActivityPreview() {
    HarmonicaShortcutTheme {
        BottomNavigationCompose(
            harmonicaKeyViewModel = HarmonicaKeyViewModel(),
            harmonicaLayoutViewModel = HarmonicaLayoutViewModel(),
            songKeyViewModel = SongKeyViewModel()
        ) {
            // do nothing
        }
    }
}