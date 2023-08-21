package com.favoratti.harmonicashortcut.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.favoratti.harmonicashortcut.data.Data

class SongKeyViewModel : ViewModel() {

    private val mSelectedKeyMutableState = mutableStateOf("")
    val selectedKeyState: State<String> = mSelectedKeyMutableState

    private val mScaleMutableState = mutableStateOf("")
    val scaleState: State<String> = mScaleMutableState

    private val mScaleKeyMutableState = mutableStateOf("")
    val scaleKeyState: State<String> = mScaleKeyMutableState

    private val mPositionMapState = mutableStateOf<Map<Int, ArrayList<Boolean>>?>(null)
    val positionMapState: State<Map<Int, ArrayList<Boolean>>?> = mPositionMapState

    private val mNumberNoteState = mutableStateOf(true)
    val numberNoteState: State<Boolean> = mNumberNoteState

    private val mFirstPositionMutableState = mutableStateOf("")
    val firstPositionState: State<String> = mFirstPositionMutableState

    private val mSecondPositionMutableState = mutableStateOf("")
    val secondPositionState: State<String> = mSecondPositionMutableState

    private val mThirdPositionMutableState = mutableStateOf("")
    val thirdPositionState: State<String> = mThirdPositionMutableState

    init {
        onKeyClick(DEFAULT_KEY)
    }

    fun onKeyClick(key: String) {

        val isMinor = key.contains("m")

        mSelectedKeyMutableState.value = key

        val first = if (isMinor) {
            Data.keysMinor.translateForwardFromKey(value = selectedKeyState.value, quantity = 3)
        } else {
            selectedKeyState.value
        }
        val second = if (isMinor) {
            Data.keysMinor.translateBackwardFromKey(value = selectedKeyState.value, quantity = 4)
        } else {
            Data.keys.translateBackwardFromKey(value = selectedKeyState.value, quantity = 7)
        }
        val third = if (isMinor) {
            Data.keysMinor.translateBackwardFromKey(value = selectedKeyState.value, quantity = 2)
        } else {
            Data.keys.translateBackwardFromKey(value = selectedKeyState.value, quantity = 5)
        }

        mFirstPositionMutableState.value = first.removeMinor()
        mSecondPositionMutableState.value = second.removeMinor()
        mThirdPositionMutableState.value = third.removeMinor()
    }

    fun onKeyScaleSelectionClick(key: String, position: Int) {
        mScaleKeyMutableState.value = key

        mScaleMutableState.value = Data.arrayPosition[position]

        mPositionMapState.value = Data.arrayPositionMap[position]
    }

    fun setNumberNoteState() {
        mNumberNoteState.value = mNumberNoteState.value.not()
    }

    private fun String.removeMinor() = this.replace("m", "")

    companion object {
        private const val DEFAULT_KEY = "G"
    }
}