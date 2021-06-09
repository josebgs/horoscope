package com.canvas.horoscope

import androidx.lifecycle.*
import api.AstrologerApi

class ZodiacDetailViewModel: ViewModel() {
    val backend: AstrologerApi = AstrologerApi.getApiService()
    private val zodiacRepository = ZodiacRepository.get()
    private val zodiacIdLiveData = MutableLiveData<Int>()

    var crimeLiveData: LiveData<Zodiac?> =
        Transformations.switchMap(zodiacIdLiveData){ zodiacId ->
            zodiacRepository.getSign(zodiacId)
        }

    fun loadSign(zodiacId: Int){
        zodiacIdLiveData.value = zodiacId
    }

}