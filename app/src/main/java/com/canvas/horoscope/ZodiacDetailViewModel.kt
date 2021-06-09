package com.canvas.horoscope

import androidx.lifecycle.*
import api.AstrologerApi
import api.HoroscopeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ZodiacDetailViewModel: ViewModel() {
    private val backend = AstrologerApi.getApiService()
    private val zodiacRepository = ZodiacRepository.get()
    private val zodiacIdLiveData = MutableLiveData<Int>()

    var crimeLiveData: LiveData<Zodiac?> =
        Transformations.switchMap(zodiacIdLiveData){ zodiacId ->
            zodiacRepository.getSign(zodiacId)
        }

    fun loadSign(zodiacId: Int){
        zodiacIdLiveData.value = zodiacId
    }

    var horoscopeLiveData: LiveData<HoroscopeResponse> = Transformations.switchMap(crimeLiveData){ zodiac ->
        liveData(Dispatchers.IO) {
            emit(zodiac?.name?.let { backend.fetchHoroscope(it.lowercase()) })
        }
    }
}