package com.attia.wazaker.ui.fragments.azkaar.specificazkaar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.attia.wazaker.databinding.FragmentSpecificAzkaarBinding
import com.attia.wazaker.ui.fragments.azkaar.MainAzkaarAdapter



class SpecificAzkaarFragment : Fragment(), MainAzkaarAdapter.OnItemClickListener {




    private lateinit var binding: FragmentSpecificAzkaarBinding
    private lateinit var viewModel: SpecificAzkaarViewModel
    val args: SpecificAzkaarFragmentArgs by navArgs()

    private lateinit var submittedList: List<SpecficAzkaarType>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSpecificAzkaarBinding.inflate(inflater, container, false)

        viewModel= ViewModelProvider(this)[SpecificAzkaarViewModel::class.java]

        viewModel.adListInit(args.cardposition)


        val specificAzkaarAdapter = SpecificAzkaarAdapter(this)

        binding.apply {
            rvSpecificAzkaar.apply {
                adapter = specificAzkaarAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }


        viewModel.adList.observe(viewLifecycleOwner, Observer { it ->
            specificAzkaarAdapter.submitList(it)

        })

        return binding.root

    }

    override fun onItemClick(position: Int) {
        viewModel.progressTracker(position)
    }
}