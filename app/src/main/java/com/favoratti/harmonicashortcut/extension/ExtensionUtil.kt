package com.favoratti.harmonicashortcut.extension

import com.favoratti.harmonicashortcut.data.Data

fun String.toFlat() = Data.sharpToFlat[this] ?: this

fun String.toSharp() = Data.flatToSharp[this] ?: this