package com.canvas.horoscope

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Zodiac(@PrimaryKey val id: Int,
                  var name: String = "",
                  var description: String = "",
                  var symbol: String = "",
                  var month: String = "")