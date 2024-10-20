package com.attia.wazaker.ui.fragments.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.attia.wazaker.R
import com.attia.wazaker.databinding.FragmentHistoryBinding
import com.attia.wazaker.ui.SwipeToDeleteCallBack
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[HistoryViewModel::class.java]


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.historyViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        val historyAdapter = HistoryAdapter()


        binding.rvhistory.apply {
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        val swipeToDeleteCallBack = object : SwipeToDeleteCallBack() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = historyAdapter.currentList[viewHolder.absoluteAdapterPosition]
                viewModel.deleteHistoryItem(item)
                Toast.makeText(requireContext(),
                    getString(R.string.zekr_deleted), Toast.LENGTH_SHORT).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(binding.rvhistory)

        viewModel.historyList.observe(viewLifecycleOwner) {
            historyAdapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}