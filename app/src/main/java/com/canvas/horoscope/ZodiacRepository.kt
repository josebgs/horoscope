package com.canvas.horoscope

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import api.AstrologerApi
import api.HoroscopeResponse
import database.ZodiacDatabase
import java.lang.IllegalStateException
import java.util.*

private const val DATABASE_NAME = "zodiac_db"
class ZodiacRepository private constructor(context: Context){

    private val database: ZodiacDatabase = Room.databaseBuilder(
        context.applicationContext,
        ZodiacDatabase::class.java,
        DATABASE_NAME)
        .createFromAsset(DATABASE_NAME)
        .build()

    private val zodiacDao = database.zodiacDao()
    private val backend: AstrologerApi = AstrologerApi.getApiService()

    fun getSigns(): LiveData<List<Zodiac>> = zodiacDao.getSigns()
    fun getSign(id:Int): LiveData<Zodiac?> = zodiacDao.getSign(id)
    suspend fun getHoroscope(sign: String): HoroscopeResponse = backend.fetchHoroscope(sign)

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