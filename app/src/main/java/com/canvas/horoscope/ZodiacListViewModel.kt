package com.canvas.horoscope

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ZodiacListViewModel: ViewModel() {
    private val zodiacRepository = ZodiacRepository.get()
    val zodiacListLiveData = zodiacRepository.getSigns()
}