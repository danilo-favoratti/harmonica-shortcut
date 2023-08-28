package com.favoratti.harmonicashortcut.composable.layout

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.favoratti.harmonicashortcut.composable.section.AllInOneSection
import com.favoratti.harmonicashortcut.composable.section.HarmonicaKeySection
import com.favoratti.harmonicashortcut.composable.section.SongKeySection
import com.favoratti.harmonicashortcut.navigation.Navigation
import com.favoratti.harmonicashortcut.navigation.bottomNavigationItems
import com.favoratti.harmonicashortcut.ui.theme.HarmonicaShortcutTheme
import com.favoratti.harmonicashortcut.viewmodel.HarmonicaKeyViewModel
import com.favoratti.harmonicashortcut.viewmodel.HarmonicaLayoutViewModel
import com.favoratti.harmonicashortcut.viewmodel.SongKeyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationCompose(
    harmonicaKeyViewModel: HarmonicaKeyViewModel,
    harmonicaLayoutViewModel: HarmonicaLayoutViewModel,
    songKeyViewModel: SongKeyViewModel,
    openWebLink: (String) -> Unit
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavigationItems.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = stringResource(screen.contentDescriptorId),
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(screen.resourceId),
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            )
                        },
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Navigation.Harmonica.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Navigation.Harmonica.route) {
                HarmonicaKeySection(
                    harmonicaKeyViewModel,
                    harmonicaLayoutViewModel
                )
            }
            composable(Navigation.Song.route) {
                SongKeySection(
                    songKeyViewModel = songKeyViewModel,
                    harmonicaLayoutViewModel = harmonicaLayoutViewModel
                )
            }
            composable(Navigation.Tips.route) {
                AllInOneSection(
                    harmonicaLayoutViewModel = harmonicaLayoutViewModel,
                    openWebLink = openWebLink
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
fun BottomNavigationComposePreview() {
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