package com.canvas.horoscope

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

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
}