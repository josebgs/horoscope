package database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.canvas.horoscope.Zodiac

@Database(entities = [Zodiac::class], version = 1)
abstract class ZodiacDatabase: RoomDatabase() {
    abstract fun zodiacDao(): ZodiacDao
}