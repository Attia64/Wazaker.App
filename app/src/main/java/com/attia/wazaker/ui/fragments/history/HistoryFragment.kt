package com.attia.wazaker.ui.fragments.history

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.attia.wazaker.databinding.FragmentHistoryBinding

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

        return binding.root
    }
}