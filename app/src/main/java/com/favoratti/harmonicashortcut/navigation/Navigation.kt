package com.favoratti.harmonicashortcut.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.favoratti.harmonicashortcut.R

sealed class Navigation(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
    @StringRes val contentDescriptorId: Int
) {

    data object Harmonica : Navigation(
        route = "harmonica",
        resourceId = R.string.bottom_navigation_harmonica,
        icon = Icons.Filled.Home,
        contentDescriptorId = R.string.bottom_navigation_harmonica_content_descriptor
    )

    data object Song :
        Navigation(
            route = "song",
            resourceId = R.string.bottom_navigation_song,
            icon = Icons.Filled.Face,
            contentDescriptorId = R.string.bottom_navigation_song_content_descriptor
        )

    data object Tips :
        Navigation(
            route = "tips",
            resourceId = R.string.bottom_navigation_tips,
            icon = Icons.Filled.Build,
            contentDescriptorId = R.string.bottom_navigation_tips_content_descriptor
        )
}

val bottomNavigationItems = listOf(
    Navigation.Harmonica,
    Navigation.Song,
    Navigation.Tips,
)