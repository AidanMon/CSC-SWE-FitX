package com.example.fitx.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fitx.R
import com.example.fitx.databinding.HealthyTipsBinding
import com.example.fitx.model.SportTips
import com.example.fitx.repository.TipsRepository

class HealthyTips: Fragment() {
    private var _binding: HealthyTipsBinding? = null
    private var tipsList2 = ArrayList<SportTips>()
    private val tipsRepository = TipsRepository()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HealthyTipsBinding.inflate(inflater, container, false)

        val tipsListView = binding.tipsListView
        tipsRepository.getHealthyTips { tips ->
           tipsList2.add(tips);
            val adapter = TipsAdapter(requireActivity(),tipsList2)
            tipsListView.adapter = adapter
        }

        return binding.root
    }

    class TipsAdapter(context: Context, private val tips: List<SportTips>) : BaseAdapter() {
        private val inflater: LayoutInflater = LayoutInflater.from(context)

        override fun getCount(): Int = tips.size

        override fun getItem(position: Int): Any = tips[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View
            val viewHolder: ViewHolder

            if (convertView == null) {
                view = inflater.inflate(R.layout.list_item_tip, parent, false)
                viewHolder = ViewHolder(view)
                view.tag = viewHolder
            } else {
                view = convertView
                viewHolder = view.tag as ViewHolder
            }

            val tip = tips[position]
            viewHolder.tipName.text = tip.tipName
            viewHolder.tipDesc.text = tip.tipDesc

            return view
        }

        private class ViewHolder(view: View) {
            val tipName: TextView = view.findViewById(R.id.tipName)
            val tipDesc: TextView = view.findViewById(R.id.tipDesc)
        }
    }

}