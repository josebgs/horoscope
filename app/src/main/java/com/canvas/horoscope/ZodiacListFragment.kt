package com.canvas.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ZodiacListFragment:Fragment() {

    private val zodiacListViewModel: ZodiacListViewModel by lazy{
        ViewModelProvider(this@ZodiacListFragment).get(ZodiacListViewModel::class.java)
    }
    private lateinit var zodiacRecyclerView: RecyclerView
    private lateinit var adapter: ZodiacAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_zodiac_list, container, false)
        zodiacRecyclerView = view.findViewById(R.id.zodiac_recycler_view) as RecyclerView
        zodiacRecyclerView.layoutManager = LinearLayoutManager(context)

        val dividerItemDecoration = DividerItemDecoration( //add div between items in recyclerview
            zodiacRecyclerView.context, (zodiacRecyclerView.layoutManager as LinearLayoutManager).orientation)

        zodiacRecyclerView.addItemDecoration(dividerItemDecoration)


        updateUI()
        return view
    }

    private fun updateUI() {
        val signs = zodiacListViewModel.signs
        adapter = ZodiacAdapter(signs)
        zodiacRecyclerView.adapter = adapter
    }

    companion object{
        fun newInstance(): ZodiacListFragment{
            return ZodiacListFragment()
        }
    }

    private inner class ZodiacHolder(view: View): RecyclerView.ViewHolder(view){
        private val zodiacNameTextView: TextView = view.findViewById(R.id.zodiac_name)
        private lateinit var sign : Zodiac

        fun bind(sign: Zodiac){
            this.sign = sign
            zodiacNameTextView.text = getString(this.sign.signId)
        }
    }

    private inner class ZodiacAdapter(var signs: List<Zodiac>): RecyclerView.Adapter<ZodiacHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZodiacHolder {
            val view = layoutInflater.inflate(R.layout.list_item_zodiac, parent, false)
            return ZodiacHolder(view)
        }

        override fun onBindViewHolder(holder: ZodiacHolder, position: Int) {
            val sign = signs[position]
            holder.bind(sign)
        }

        override fun getItemCount() = signs.size

    }
}