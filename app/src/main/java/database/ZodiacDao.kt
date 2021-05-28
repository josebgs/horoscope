package database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.canvas.horoscope.Zodiac
import java.util.*

@Dao
interface ZodiacDao {

    @Query("SELECT * FROM zodiac")
    fun getSigns(): LiveData<List<Zodiac>>

    @Query("SELECT * FROM zodiac WHERE id=(:id)")
    fun getSign(id:Int): LiveData<Zodiac?>
}