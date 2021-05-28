package com.canvas.horoscope

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "ZodiacListFragment"
class ZodiacListFragment:Fragment() {

    private val zodiacListViewModel: ZodiacListViewModel by lazy{
        ViewModelProvider(this@ZodiacListFragment).get(ZodiacListViewModel::class.java)
    }
    private lateinit var zodiacRecyclerView: RecyclerView
    private var adapter: ZodiacAdapter? = ZodiacAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_zodiac_list, container, false)
        zodiacRecyclerView = view.findViewById(R.id.zodiac_recycler_view) as RecyclerView
        zodiacRecyclerView.layoutManager = LinearLayoutManager(context)
        zodiacRecyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration( //add div between items in recyclerview
            zodiacRecyclerView.context, (zodiacRecyclerView.layoutManager as LinearLayoutManager).orientation)

        zodiacRecyclerView.addItemDecoration(dividerItemDecoration)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        zodiacListViewModel.zodiacListLiveData.observe(
            viewLifecycleOwner,
            { signs ->
                signs?.let{
                    updateUI(signs)
                }
            })

    }
    private fun updateUI(signs: List<Zodiac>) {
        adapter = ZodiacAdapter(signs)
        zodiacRecyclerView.adapter = adapter
    }

    companion object{
        fun newInstance(): ZodiacListFragment{
            return ZodiacListFragment()
        }
    }

    private inner class ZodiacHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener{
        private val zodiacNameTextView: TextView = view.findViewById(R.id.zodiac_name)
        private lateinit var zodiac : Zodiac

        init{
            itemView.setOnClickListener(this)
        }

        fun bind(zodiac: Zodiac){
            this.zodiac = zodiac
            zodiacNameTextView.text = zodiac.name
        }

        override fun onClick(v: View) {
            Log.d(TAG, "${this.zodiac.name} was clicked")
            val direction = ZodiacListFragmentDirections.actionZodiacListFragmentToZodiacFragment(id = zodiac.id.toString())
            v.findNavController().navigate(direction)
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