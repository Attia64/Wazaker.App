package com.attia.wazaker.ui.fragments.azkaar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.attia.wazaker.R
import com.attia.wazaker.databinding.FragmentAzkaarBinding

class AzkaarFragment : Fragment() {


    private var _binding: FragmentAzkaarBinding? = null
    private val binding get() = _binding!!

    private val dataList: List<MainAzkaarType> = listOf(
        MainAzkaarType("أذكاري", R.drawable.beads),
        MainAzkaarType("أذكار الصباح", R.drawable.sun),
        MainAzkaarType("أذكار المساء", R.drawable.night),
        MainAzkaarType("أذكار ختم الصلاة", R.drawable.ramadan),
        MainAzkaarType("أذكار النوم", R.drawable.sleep),
        MainAzkaarType("فضل الذكر", R.drawable.star)
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAzkaarBinding.inflate(inflater, container, false)



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainAzkaarAdapter = MainAzkaarAdapter(listener = { position ->
            when (position) {
                0 -> findNavController().navigate(R.id.action_azkaarFragment_to_myAzkaarFragment)
                1, 2, 3, 4, 5 -> findNavController().navigate(
                    AzkaarFragmentDirections.actionAzkaarFragmentToSpecificAzkaarFragment(
                        position
                    )
                )
            }

        })


        binding.rvMainAzkaar.apply {
            adapter = mainAzkaarAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
            mainAzkaarAdapter.submitList(dataList)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}