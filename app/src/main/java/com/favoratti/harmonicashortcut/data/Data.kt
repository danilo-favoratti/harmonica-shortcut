package com.favoratti.harmonicashortcut.data

object Data {
    val keys: CircularList<String>
        get() = CircularList(
            listOf(
                "A",
                "A#",
                "B",
                "C",
                "C#",
                "D",
                "D#",
                "E",
                "F",
                "F#",
                "G",
                "G#"
            )
        )

    val keysMinor: CircularList<String>
        get() = CircularList(
            listOf(
                "Am",
                "A#m",
                "Bm",
                "Cm",
                "C#m",
                "Dm",
                "D#m",
                "Em",
                "Fm",
                "F#m",
                "Gm",
                "G#m"
            )
        )

    const val firstPositionScale = "1 -1 2 -2(3) -3’’ 4 -4 5 6 -6 7 -8 8 9 -10 10"

    const val secondPositionScale = "-1 2 -2 -3’’ -3’ -3 -4 5 6 -6 -7 -8 8 9 -10"

    const val secondPositionScaleExtraBends =
        "-1 2 -2 -3’’ -3’ -3 -4’ -4 5 6 -6’ -6 -7 -8 8’ 8 9 -10"

    const val thirdPositionScale = "1 -1 -2’’ -2(3) -3’’’ -3’’ 4 -4 -5 6 -6’ -6 7 -8 -9 9 -10 10"

    val arrayPosition = arrayListOf(
        "",
        firstPositionScale,
        secondPositionScaleExtraBends,
        thirdPositionScale,
        firstPositionScale,
        secondPositionScaleExtraBends,
        "",
        "",
        "",
        "",
        "",
        "",
        thirdPositionScale
    )

    val harmonicaPosition: Map<Int, ArrayList<Int?>> = mutableMapOf<Int, ArrayList<Int?>>().apply {
        this[OVER_BLOW_ONE] = arrayListOf(null, null, null, null, null, null, null, null, null, 10)
        this[OVER_BLOW_HALF] = arrayListOf(null, null, null, null, null, null, null, 3, 6, 11)
        this[BLOW] = arrayListOf(0, 4, 7, 0, 4, 7, 0, 4, 7, 0)
        this[DRAW] = arrayListOf(2, 7, 11, 2, 5, 9, 11, 2, 5, 9)
        this[BEND_HALF] = arrayListOf(1, 6, 10, 1, null, 8, null, null, null, null)
        this[BEND_ONE] = arrayListOf(null, 5, 9, null, null, null, null, null, null, null)
        this[BEND_ONE_HALF] = arrayListOf(null, null, 8, null, null, null, null, null, null, null)
    }.toMap()

    private val firstPositionMap: Map<Int, ArrayList<Boolean>> =
        mutableMapOf<Int, ArrayList<Boolean>>().apply {
            this[OVER_BLOW_ONE] =
                arrayListOf(false, false, false, false, false, false, false, false, false, false)
            this[OVER_BLOW_HALF] =
                arrayListOf(false, false, false, false, false, false, false, false, false, false)
            this[BLOW] = arrayListOf(true, true, true, true, true, true, true, true, true, true)
            this[DRAW] = arrayListOf(true, true, false, true, false, true, false, true, false, true)
            this[BEND_HALF] =
                arrayListOf(false, false, false, false, false, false, false, false, false, false)
            this[BEND_ONE] =
                arrayListOf(false, false, true, false, false, false, false, false, false, false)
            this[BEND_ONE_HALF] =
                arrayListOf(false, false, false, false, false, false, false, false, false, false)
        }.toMap()

    private val secondPositionMap: Map<Int, ArrayList<Boolean>> =
        mutableMapOf<Int, ArrayList<Boolean>>().apply {
            this[OVER_BLOW_ONE] =
                arrayListOf(false, false, false, false, false, false, false, false, false, false)
            this[OVER_BLOW_HALF] =
                arrayListOf(false, false, false, false, false, false, false, true, false, false)
            this[BLOW] =
                arrayListOf(false, true, false, false, true, true, false, true, true, false)
            this[DRAW] = arrayListOf(true, true, true, true, false, true, true, true, false, true)
            this[BEND_HALF] =
                arrayListOf(false, false, true, true, false, true, false, false, false, false)
            this[BEND_ONE] =
                arrayListOf(false, false, true, false, false, false, false, false, false, false)
            this[BEND_ONE_HALF] =
                arrayListOf(false, false, false, false, false, false, false, false, false, false)
        }.toMap()

    private val thirdPositionMap: Map<Int, ArrayList<Boolean>> =
        mutableMapOf<Int, ArrayList<Boolean>>().apply {
            this[OVER_BLOW_ONE] =
                arrayListOf(false, false, false, false, false, false, false, false, false, false)
            this[OVER_BLOW_HALF] =
                arrayListOf(false, false, false, false, false, false, false, false, false, false)
            this[BLOW] = arrayListOf(true, false, true, true, false, true, true, false, true, true)
            this[DRAW] = arrayListOf(true, true, false, true, true, true, false, true, true, true)
            this[BEND_HALF] =
                arrayListOf(false, false, false, false, false, true, false, false, false, false)
            this[BEND_ONE] =
                arrayListOf(false, true, true, false, false, false, false, false, false, false)
            this[BEND_ONE_HALF] =
                arrayListOf(false, false, true, false, false, false, false, false, false, false)
        }.toMap()

    val arrayPositionMap = arrayListOf(
        null,
        firstPositionMap,
        secondPositionMap,
        thirdPositionMap,
        firstPositionMap,
        secondPositionMap,
        null,
        null,
        null,
        null,
        null,
        null,
        thirdPositionMap
    )

    val sharpToFlat: Map<String, String> = mutableMapOf<String, String>().apply {
        this["A#"] = "Bb"
        this["C#"] = "Db"
        this["D#"] = "Eb"
        this["F#"] = "Gb"
        this["G#"] = "Ab"
    }

    val flatToSharp: Map<String, String> = mutableMapOf<String, String>().apply {
        this["Bb"] = "A#"
        this["Db"] = "C#"
        this["Eb"] = "D#"
        this["Gb"] = "F#"
        this["Ab"] = "G#"
    }

    private const val OVER_BLOW_ONE = 0
    private const val OVER_BLOW_HALF = 1
    private const val BLOW = 2
    private const val DRAW = 3
    private const val BEND_HALF = 4
    private const val BEND_ONE = 5
    private const val BEND_ONE_HALF = 6
}