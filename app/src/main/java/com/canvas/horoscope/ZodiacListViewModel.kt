package com.canvas.horoscope

import androidx.lifecycle.ViewModel

class ZodiacListViewModel: ViewModel() {
    var signs = mutableListOf<Zodiac>()

    init{
        signs = listOf(
            Zodiac(R.string.aries),
            Zodiac(R.string.aquarius),
            Zodiac(R.string.pisces),
            Zodiac(R.string.taurus),
            Zodiac(R.string.gemini),
            Zodiac(R.string.cancer),
            Zodiac(R.string.leo),
            Zodiac(R.string.virgo),
            Zodiac(R.string.libra),
            Zodiac(R.string.scorpio),
            Zodiac(R.string.sagittarius),
            Zodiac(R.string.capricorn)
        ) as MutableList<Zodiac>
    }
}