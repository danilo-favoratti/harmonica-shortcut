package com.favoratti.harmonicashortcut.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.favoratti.harmonicashortcut.data.Data

class HarmonicaKeyViewModel : ViewModel() {

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

    private val mFourthPositionMutableState = mutableStateOf("")
    val fourthPositionState: State<String> = mFourthPositionMutableState

    private val mFifthPositionMutableState = mutableStateOf("")
    val fifthPositionState: State<String> = mFifthPositionMutableState

    private val mTwelfthPositionMutableState = mutableStateOf("")
    val twelfthPositionState: State<String> = mTwelfthPositionMutableState

    init {
        onKeySelectionClick(DEFAULT_KEY)
    }

    fun onKeySelectionClick(key: String) {
        mSelectedKeyMutableState.value = key.uppercase()

        val second =
            Data.keys.translateBackwardFromKey(value = selectedKeyState.value, quantity = 5)
        val third =
            Data.keys.translateBackwardFromKey(value = selectedKeyState.value, quantity = 5 * 2)
        val fourth =
            Data.keys.translateBackwardFromKey(value = selectedKeyState.value, quantity = 5 * 3)
        val fifth =
            Data.keys.translateBackwardFromKey(value = selectedKeyState.value, quantity = 5 * 4)
        val twelfth =
            Data.keys.translateBackwardFromKey(value = selectedKeyState.value, quantity = 5 * 11)

        mFirstPositionMutableState.value = selectedKeyState.value
        mSecondPositionMutableState.value = second
        mThirdPositionMutableState.value = "${third}m"
        mFourthPositionMutableState.value = "${fourth}m"
        mFifthPositionMutableState.value = "${fifth}m"
        mTwelfthPositionMutableState.value = twelfth
    }

    fun onKeyScaleSelectionClick(key: String, position: Int) {
        mScaleKeyMutableState.value = key

        mScaleMutableState.value = Data.arrayPosition[position]

        mPositionMapState.value = Data.arrayPositionMap[position]
    }

    fun setNumberNoteState() {
        mNumberNoteState.value = mNumberNoteState.value.not()
    }

    companion object {
        private const val DEFAULT_KEY = "C"
    }
}