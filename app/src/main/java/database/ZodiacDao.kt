package database

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.canvas.horoscope.Zodiac

interface ZodiacDao {

    @Query("SELECT * FROM zodiac")
    fun getSigns(): LiveData<List<Zodiac>>
}