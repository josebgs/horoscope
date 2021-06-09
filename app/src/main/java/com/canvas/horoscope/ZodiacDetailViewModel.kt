package com.canvas.horoscope

import androidx.lifecycle.*
import api.AstrologerApi
import api.HoroscopeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ZodiacDetailViewModel: ViewModel() {
    private val zodiacRepository = ZodiacRepository.get()
    private val zodiacIdLiveData = MutableLiveData<Int>()

    var crimeLiveData: LiveData<Zodiac?> =
        Transformations.switchMap(zodiacIdLiveData){ zodiacId ->
            zodiacRepository.getSign(zodiacId)
        }

    fun loadSign(zodiacId: Int){
        zodiacIdLiveData.value = zodiacId
    }

    suspend fun getHoroscope(sign: String): HoroscopeResponse{
        return withContext(Dispatchers.IO){
            zodiacRepository.getHoroscope(sign)
        }

    }
}