package com.favoratti.harmonicashortcut.viewmodel

import androidx.annotation.DrawableRes
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.favoratti.harmonicashortcut.R
import com.favoratti.harmonicashortcut.data.Data
import com.favoratti.harmonicashortcut.extension.toFlat
import com.favoratti.harmonicashortcut.extension.toSharp

class HarmonicaLayoutViewModel : ViewModel() {

    private val mKeyMutableState = mutableStateOf("")
    val keyState: State<String> = mKeyMutableState

    init {
        onKeyClick(DEFAULT_KEY)
    }

    fun getKeys() = Data.keys.toList()

    fun onKeyClick(key: String) {
        mKeyMutableState.value = key
    }

    fun onKeyLayoutSelection(key: String, line: Int, row: Int) =
        Data.harmonicaPosition[line]?.get(row)?.let {
            Data.keys.translateForwardFromKey(key.removeMinor().toSharp(), it).toFlat()
        } ?: ""

    fun onKeyLayoutHighlight(line: Int, row: Int, map: Map<Int, ArrayList<Boolean>>?) =
        map?.let {
            it[line]?.get(row) ?: false
        } ?: false

    private fun String.removeMinor() = this.replace("m", "")

    companion object {
        private const val DEFAULT_KEY = "C"
    }
}