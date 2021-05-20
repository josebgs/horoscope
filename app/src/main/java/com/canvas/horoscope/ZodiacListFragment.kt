package com.canvas.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class ZodiacListFragment:Fragment() {

    val zodiacListViewModel =
        ViewModelProvider(this).get(ZodiacListViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object{
        fun newInstance(): ZodiacListFragment{
            return ZodiacListFragment()
        }
    }
}