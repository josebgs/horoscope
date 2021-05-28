package com.canvas.horoscope

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs

class ZodiacFragment : Fragment() {

    private lateinit var nameTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var symbolTextView: TextView
    private lateinit var monthTextView: TextView
    private lateinit var zodiac: Zodiac
    private val zodiacDetailViewModel: ZodiacDetailViewModel by lazy{
        ViewModelProvider(this).get(ZodiacDetailViewModel::class.java)
    }

    private val safeArgs: ZodiacFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val zodiacId: Int = safeArgs.id.toInt()

        zodiacDetailViewModel.loadSign(zodiacId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_zodiac, container, false)

        nameTextView = view.findViewById(R.id.zodiac_name)
        descriptionTextView = view.findViewById(R.id.zodiac_description)
        symbolTextView = view.findViewById(R.id.zodiac_symbol)
        monthTextView = view.findViewById(R.id.zodiac_month)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        zodiacDetailViewModel.crimeLiveData.observe(
            viewLifecycleOwner,
            Observer{ sign ->
                sign?.let {
                    this.zodiac = sign
                    updateUI()
                }
            }
        )
    }

    private fun updateUI() {
        nameTextView.text = zodiac.name
        descriptionTextView.text = zodiac.description
        symbolTextView.text = zodiac.symbol
        monthTextView.text = zodiac.month
    }
}