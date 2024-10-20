package com.attia.wazaker.ui.fragments.azkaar.specificazkaar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.attia.wazaker.databinding.FragmentSpecificAzkaarBinding


class SpecificAzkaarFragment : Fragment() {


    private var _binding: FragmentSpecificAzkaarBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SpecificAzkaarViewModel
    private val args: SpecificAzkaarFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSpecificAzkaarBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[SpecificAzkaarViewModel::class.java]


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imBackButton.setOnClickListener {
            findNavController().navigate(SpecificAzkaarFragmentDirections.actionSpecificAzkaarFragmentToAzkaarFragment())
        }

        viewModel.adListInit(args.cardposition)

        val specificAzkaarAdapter = SpecificAzkaarAdapter(listener = { position ->
            viewModel.progressTracker(position)
        }, args.cardposition)


        binding.rvSpecificAzkaar.apply {
            adapter = specificAzkaarAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }



        viewModel.adList.observe(viewLifecycleOwner) {
            specificAzkaarAdapter.submitList(it)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}