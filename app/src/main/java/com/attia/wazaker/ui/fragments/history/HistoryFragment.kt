package com.attia.wazaker.ui.fragments.history

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.attia.wazaker.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)


        val historyViewModel = ViewModelProvider(this)[HistoryViewModel::class.java]

        binding.historyViewModel = historyViewModel

        binding.lifecycleOwner = viewLifecycleOwner

        val historyAdapter = HistoryAdapter()


        binding.rvhistory.apply {
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        historyViewModel.historyList.observe(viewLifecycleOwner, Observer { it ->
            historyAdapter.submitList(it)
        })

        return binding.root
    }
}