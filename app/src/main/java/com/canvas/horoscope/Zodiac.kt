package com.canvas.horoscope

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Zodiac(@PrimaryKey val sign: String)