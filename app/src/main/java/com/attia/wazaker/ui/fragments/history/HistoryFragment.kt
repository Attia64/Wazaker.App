package com.attia.wazaker.ui.fragments.history

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.attia.wazaker.databinding.FragmentHistoryBinding
import com.attia.wazaker.ui.SwipeToDeleteCallBack
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

        val swipeToDeleteCallBack = object : SwipeToDeleteCallBack() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = historyAdapter.currentList[viewHolder.absoluteAdapterPosition]
                historyViewModel.deleteHistoryItem(item)
                Toast.makeText(requireContext(), "تم حذف الذكر", Toast.LENGTH_SHORT).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(binding.rvhistory)

        historyViewModel.historyList.observe(viewLifecycleOwner, Observer { it ->
            historyAdapter.submitList(it)
        })

        return binding.root
    }
}