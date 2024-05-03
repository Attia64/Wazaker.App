package com.attia.wazaker.ui.fragments.azkaar.specificazkaar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.attia.wazaker.databinding.FragmentSpecificAzkaarBinding


class SpecificAzkaarFragment : Fragment() {


    private lateinit var binding: FragmentSpecificAzkaarBinding
    private lateinit var viewModel: SpecificAzkaarViewModel
    val args: SpecificAzkaarFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSpecificAzkaarBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[SpecificAzkaarViewModel::class.java]


        binding.imBackButton.setOnClickListener {
            findNavController().navigate(SpecificAzkaarFragmentDirections.actionSpecificAzkaarFragmentToAzkaarFragment())
        }

        viewModel.adListInit(args.cardposition)

        val specificAzkaarAdapter = SpecificAzkaarAdapter(listener = { position ->
            viewModel.progressTracker(position)
        }, args.cardposition)

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
}