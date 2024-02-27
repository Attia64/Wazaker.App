package com.attia.wazaker.ui.fragments.azkaar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.attia.wazaker.R
import com.attia.wazaker.databinding.FragmentAzkaarBinding
import com.attia.wazaker.ui.fragments.azkaar.myazkaar.MainAzkaarType

class AzkaarFragment : Fragment() {


    private lateinit var binding: FragmentAzkaarBinding
    private var dataList = ArrayList<MainAzkaarType>()
    private lateinit var mainAzkaarAdapter: MainAzkaarAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAzkaarBinding.inflate(inflater, container, false)

       // val azkaaradapter = MainAzkaarAdapter(dataList)
        mainAzkaarAdapter = MainAzkaarAdapter(dataList)

        binding.apply {
            rvMainAzkaar.apply {
                adapter = mainAzkaarAdapter
                layoutManager = GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
            }
        }

        prepareAzkaarList()
        return binding.root
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun prepareAzkaarList() {
        var azkaar =  MainAzkaarType("أذكاري", R.drawable.beads)
        dataList.add(azkaar)
        azkaar = MainAzkaarType("أذكار الصباح", R.drawable.sun)
        dataList.add(azkaar)
        azkaar = MainAzkaarType("أذكار المساء", R.drawable.night)
        dataList.add(azkaar)
        azkaar = MainAzkaarType("أذكار ختم الصلاة", R.drawable.ramadan)
        dataList.add(azkaar)

        mainAzkaarAdapter.notifyDataSetChanged()
    }
}