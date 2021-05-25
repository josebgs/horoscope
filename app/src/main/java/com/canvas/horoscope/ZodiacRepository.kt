package com.canvas.horoscope

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import database.ZodiacDatabase
import java.lang.IllegalStateException

private const val DATABASE_NAME = "zodiac-database.sqlite"
class ZodiacRepository private constructor(context: Context){

    private val database: ZodiacDatabase = Room.databaseBuilder(
        context.applicationContext,
        ZodiacDatabase::class.java,
        DATABASE_NAME)
        .createFromAsset(DATABASE_NAME)
        .build()

    private val zodiacDao = database.zodiacDao()

    fun getSigns(): LiveData<List<Zodiac>> = zodiacDao.getSigns()

    companion object{
        private var INSTANCE: ZodiacRepository? = null

        fun initialize(context: Context){
            if (INSTANCE == null)
                INSTANCE = ZodiacRepository(context)
        }

        fun get(): ZodiacRepository {
            return  INSTANCE?:
            throw IllegalStateException("ZodiacRepository must be initialized.")
        }
    }
}